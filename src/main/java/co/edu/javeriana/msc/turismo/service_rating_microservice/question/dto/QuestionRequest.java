package co.edu.javeriana.msc.turismo.service_rating_microservice.question.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record QuestionRequest(
        Long id,
        @NotNull(message = "The content of the question is required")
        String content,
        @NotNull(message = "The date of the question is required")
        LocalDate date,
        @NotNull(message = "The user id of the question is required")
        Long createdBy,
        @NotNull(message = "The service id of the question is required")
        Long serviceId
) {
}