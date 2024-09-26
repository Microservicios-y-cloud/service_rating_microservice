package co.edu.javeriana.msc.turismo.service_rating_microservice.question.dto;

import co.edu.javeriana.msc.turismo.service_rating_microservice.question.model.Answer;
import co.edu.javeriana.msc.turismo.service_rating_microservice.question.model.Person;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record QuestionRequest(
        String id,
        @NotNull(message = "The service id of the question is required")
        Long serviceId,
        @NotNull(message = "The content of the question is required")
        String content,
        @NotNull(message = "The user of the question is required")
        Person createdBy,
        List<Answer> answers

) {
}