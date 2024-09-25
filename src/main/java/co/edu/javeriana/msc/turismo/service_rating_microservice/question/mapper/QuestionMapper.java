package co.edu.javeriana.msc.turismo.service_rating_microservice.question.mapper;

import org.springframework.stereotype.Service;

import co.edu.javeriana.msc.turismo.service_rating_microservice.question.dto.QuestionRequest;
import co.edu.javeriana.msc.turismo.service_rating_microservice.question.dto.QuestionResponse;
import co.edu.javeriana.msc.turismo.service_rating_microservice.question.model.Question;

import java.time.LocalDateTime;

@Service
public class QuestionMapper {
    public Question toQuestion(QuestionRequest request) {
        return Question.builder()
                .id(request.id())
                .content(request.content())
                .date(LocalDateTime.now())
                .createdBy(request.createdBy())
                .serviceId(request.serviceId())
                .answers(request.answers())
                .build();
    }

    public QuestionResponse toQuestionResponse(Question question) {
        return new QuestionResponse(
                        question.getId(),
                        question.getServiceId(),
                        question.getContent(),
                        question.getCreatedBy(),
                        question.getAnswers()
                );
    }
}