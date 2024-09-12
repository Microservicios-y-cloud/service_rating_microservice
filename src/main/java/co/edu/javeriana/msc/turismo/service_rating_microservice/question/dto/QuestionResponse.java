package co.edu.javeriana.msc.turismo.service_rating_microservice.question.dto;

import java.time.LocalDate;

public record QuestionResponse(
        Long id,
        String content,
        LocalDate date,
        Long createdBy,
        Long serviceId
) {
}