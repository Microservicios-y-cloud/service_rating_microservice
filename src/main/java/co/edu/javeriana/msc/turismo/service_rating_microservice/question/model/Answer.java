package co.edu.javeriana.msc.turismo.service_rating_microservice.question.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Validated
public class Answer {
    @NotNull(message = "The content of the answer is required")
    private String content;
    @CreatedBy
    @NotNull(message = "The user of the answer is required")
    private Person createdBy;
    @CreatedDate
    private LocalDateTime date;
}
