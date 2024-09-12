package co.edu.javeriana.msc.turismo.service_rating_microservice.question.model;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@SuperBuilder
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String content;
    private LocalDate date;

    @CreatedBy
    @Column(nullable = false, updatable = false)
    private Long createdBy;

    @Column(nullable = false, updatable = false)
    private Long serviceId;
}