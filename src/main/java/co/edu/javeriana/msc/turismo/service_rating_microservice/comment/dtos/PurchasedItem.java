package co.edu.javeriana.msc.turismo.service_rating_microservice.comment.dtos;

import co.edu.javeriana.msc.turismo.service_rating_microservice.enums.PaymentStatus;
import co.edu.javeriana.msc.turismo.service_rating_microservice.enums.Status;
import co.edu.javeriana.msc.turismo.service_rating_microservice.queue.dtos.SuperService;

public record PurchasedItem(
        Double subtotal,
        Integer quantity,
        SuperService service,
        Status orderStatus,
        PaymentStatus paymentStatus
) {
}