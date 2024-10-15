package co.edu.javeriana.msc.turismo.service_rating_microservice.queue;

import co.edu.javeriana.msc.turismo.service_rating_microservice.comment.dtos.PurchasedInformation;
import co.edu.javeriana.msc.turismo.service_rating_microservice.comment.dtos.PurchasedItem;
import co.edu.javeriana.msc.turismo.service_rating_microservice.comment.model.HistoricoServicios;
import co.edu.javeriana.msc.turismo.service_rating_microservice.comment.repository.HistoricoServiciosRepository;
import co.edu.javeriana.msc.turismo.service_rating_microservice.queue.dtos.SuperServiceDTO;
import co.edu.javeriana.msc.turismo.service_rating_microservice.queue.repository.SuperServiceRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;


@Slf4j
@Configuration
@AllArgsConstructor
public class MessageQueueConsumer {
    private final SuperServiceRepository repository;
    private final HistoricoServiciosRepository historicoServiciosRepository;

    @Bean
    Consumer<Message<SuperServiceDTO>> receiveMessage() {
        return message -> {
            log.info("Received message: {}", message);
            log.info("Payload: {}", message.getPayload());
            //hacemos un switch case en caso de que sea CREATE, UPDATE, O DELETE
            switch (message.getPayload().getEventType()) {
                case CREATE:
                    repository.save(message.getPayload().getSuperService());
                    log.info("Service saved: {}", message.getPayload().getSuperService());
                    break;
                case UPDATE:
                    var serviceToUpdate = repository.findById(message.getPayload().getSuperService().id()).orElse(null);
                    if(serviceToUpdate != null) {
                        repository.save(message.getPayload().getSuperService());
                        log.info("Service updated: {}", message.getPayload().getSuperService().id());
                    } else {
                        log.error("Service not found: {}", message.getPayload().getSuperService().id());
                    }
                    break;
                case DELETE:
                    repository.deleteById(message.getPayload().getSuperService().id());
                    log.info("Service deleted: {}", message.getPayload().getSuperService().id());
                    break;
                default:
                    log.error("Invalid action: {}", message.getPayload().getEventType());
                    break;
            }
        };
    }

@Bean
Consumer<Message<PurchasedInformation>> receiveOrderQualification() {
    return message -> {
        log.info("Received message about the customer: {}", message);
        log.info("Payload: {}", message.getPayload());
        
        // Obtener la información de la compra
        var purchasedInformation = message.getPayload();
        
        // Intentar recuperar el registro existente de la base de datos usando el userId
        Optional<HistoricoServicios> existingRecordOpt = historicoServiciosRepository.findById(purchasedInformation.getUserId());
        
        HistoricoServicios historicoServicios;
        if (existingRecordOpt.isPresent()) {
            // Si el registro existe, se actualizan los campos correspondientes
            historicoServicios = existingRecordOpt.get();

            // Obtener los items actuales
            List<PurchasedItem> existingItems = historicoServicios.getPurchasedItems();

            // Filtrar los nuevos items para evitar duplicados (basado en serviceId)
            List<PurchasedItem> newItems = purchasedInformation.getPurchasedItems()
                .stream()
                .filter(newItem -> existingItems.stream()
                    .noneMatch(existingItem -> existingItem.service().id().equals(newItem.service().id())))
                .collect(Collectors.toList());

            // Agregar solo los nuevos items
            if (!newItems.isEmpty()) {
                log.info("Adding {} new items to existing purchase history.", newItems.size());
                existingItems.addAll(newItems);
            } else {
                log.info("No new items to add.");
            }

            log.info("Updating existing record for userId: {}", purchasedInformation.getUserId());
        } else {
            // Si no existe, se crea un nuevo registro
            historicoServicios = new HistoricoServicios(
                purchasedInformation.getUserId(),
                purchasedInformation.getCreationDate(),
                purchasedInformation.getPurchaser(),
                purchasedInformation.getAmount(),
                purchasedInformation.getPurchasedItems()
            );
            historicoServiciosRepository.save(historicoServicios);
        }

        // Guardar los cambios
        historicoServiciosRepository.save(historicoServicios);
    };
}

}
