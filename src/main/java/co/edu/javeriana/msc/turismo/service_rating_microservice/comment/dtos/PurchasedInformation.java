package co.edu.javeriana.msc.turismo.service_rating_microservice.comment.dtos;


import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class PurchasedInformation implements Serializable{
        String userId;
        LocalDateTime creationDate;
        LocalDateTime lastUpdate;
        Customer purchaser;
        List<PurchasedItem> purchasedItems;
        BigDecimal amount;
}