package co.edu.javeriana.msc.turismo.service_rating_microservice.comment.model;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import co.edu.javeriana.msc.turismo.service_rating_microservice.comment.dtos.Customer;
import co.edu.javeriana.msc.turismo.service_rating_microservice.comment.dtos.PurchasedItem;
import co.edu.javeriana.msc.turismo.service_rating_microservice.enums.PaymentStatus;
import co.edu.javeriana.msc.turismo.service_rating_microservice.enums.Status;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class HistoricoServicios {
    @Id
    private String id;
    @CreatedDate
    private LocalDateTime creationDate;
    private Customer user;
    private BigDecimal price;
    private List<PurchasedItem> purchasedItems;
}