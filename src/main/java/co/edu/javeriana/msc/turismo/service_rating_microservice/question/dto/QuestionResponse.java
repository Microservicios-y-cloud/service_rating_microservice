package co.edu.javeriana.msc.turismo.service_rating_microservice.question.dto;

import co.edu.javeriana.msc.turismo.service_rating_microservice.question.model.Answer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record QuestionResponse(
        String id,
        Long serviceId,
        String content,
        String createdBy,
        List<Answer> answers

) {
}