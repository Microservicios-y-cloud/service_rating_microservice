package co.edu.javeriana.msc.turismo.service_rating_microservice.question.mapper;

import org.springframework.stereotype.Service;

import co.edu.javeriana.msc.turismo.service_rating_microservice.question.dto.QuestionRequest;
import co.edu.javeriana.msc.turismo.service_rating_microservice.question.dto.QuestionResponse;
import co.edu.javeriana.msc.turismo.service_rating_microservice.question.model.Question;

@Service
public class QuestionMapper {
    public Question toQuestion(QuestionRequest request) {
        return Question.builder()
                .id(request.id())
                .content(request.content())
                .date(request.date())
                .createdBy(request.createdBy())
                .serviceId(request.serviceId())
                .build();
    }

    public QuestionResponse toQuestionResponse(Question question) {
        return new QuestionResponse(
                        question.getId(),
                        question.getContent(),
                        question.getDate(),
                        question.getCreatedBy(),
                        question.getServiceId()
                        );
    }
}