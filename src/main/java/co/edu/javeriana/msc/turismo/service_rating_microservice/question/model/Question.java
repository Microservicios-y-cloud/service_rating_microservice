package co.edu.javeriana.msc.turismo.service_rating_microservice.question.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class Question {
    @Id
    private String id;
    private Long serviceId;

    @CreatedDate
    private LocalDateTime date;
    @LastModifiedDate
    private LocalDateTime lastUpdate;

    private String content;
    @CreatedBy
    private String createdBy;

    private List<Answer> answers;
}