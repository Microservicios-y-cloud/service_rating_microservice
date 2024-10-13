package co.edu.javeriana.msc.turismo.service_rating_microservice.comment.model;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.*;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import co.edu.javeriana.msc.turismo.service_rating_microservice.comment.dtos.Customer;
import co.edu.javeriana.msc.turismo.service_rating_microservice.comment.enums.Qualification;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document

public class Comment {
    @Id
    private String id;
    private Long serviceId;
    @CreatedDate
    private LocalDateTime date;
    @LastModifiedDate
    private LocalDateTime lastUpdate;
    String content;
    Qualification qualification;

    @CreatedBy
    private Customer createdBy;
}