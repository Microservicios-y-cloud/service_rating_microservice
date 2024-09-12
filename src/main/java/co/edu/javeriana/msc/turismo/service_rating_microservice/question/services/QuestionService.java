package co.edu.javeriana.msc.turismo.service_rating_microservice.question.services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import co.edu.javeriana.msc.turismo.service_rating_microservice.question.dto.QuestionRequest;
import co.edu.javeriana.msc.turismo.service_rating_microservice.question.dto.QuestionResponse;
import co.edu.javeriana.msc.turismo.service_rating_microservice.question.mapper.QuestionMapper;
import co.edu.javeriana.msc.turismo.service_rating_microservice.question.repository.QuestionRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class QuestionService{
  
  private final QuestionRepository repository;
  private final QuestionMapper mapper;

  public Long createQuestion (@Valid QuestionRequest request) {
    var Question = repository.save(mapper.toQuestion(request));
    return Question.getId();
  }

  public QuestionResponse findById(Long questionId) {
    return repository.findById(questionId)
            .map(mapper::toQuestionResponse)
            .orElseThrow(() -> new EntityNotFoundException("Question not found, with id: " + questionId));
}

  public List<QuestionResponse> findAllQuestions() {
    return repository.findAll()
            .stream()
            .map(mapper::toQuestionResponse)
            .collect(Collectors.toList());
  }

  public QuestionResponse updateQuestionContent(Long questionId, QuestionRequest questionRequest) {
    var question = repository.findById(questionId)
            .orElseThrow(() -> new EntityNotFoundException("Question not found with id: " + questionId));

    // Actualizar solo el contenido de la pregunta
    question.setContent(questionRequest.content());

    // Guardar los cambios
    repository.save(question);

    // Devolver la respuesta actualizada
    return mapper.toQuestionResponse(question);
}

  public void deleteCustomer(Long questionId) {
    repository.deleteById(questionId);
  }
}
