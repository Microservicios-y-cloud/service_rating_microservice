package co.edu.javeriana.msc.turismo.service_rating_microservice.question.dto;

import co.edu.javeriana.msc.turismo.service_rating_microservice.question.model.Answer;
import co.edu.javeriana.msc.turismo.service_rating_microservice.question.model.Person;

import java.util.List;

public record QuestionResponse(
        String id,
        Long serviceId,
        String content,
        Person createdBy,
        List<Answer> answers

) {
}