package co.edu.javeriana.msc.turismo.service_rating_microservice.question.services;

import co.edu.javeriana.msc.turismo.service_rating_microservice.question.model.Answer;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import co.edu.javeriana.msc.turismo.service_rating_microservice.question.dto.QuestionRequest;
import co.edu.javeriana.msc.turismo.service_rating_microservice.question.dto.QuestionResponse;
import co.edu.javeriana.msc.turismo.service_rating_microservice.question.mapper.QuestionMapper;
import co.edu.javeriana.msc.turismo.service_rating_microservice.question.repository.QuestionRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class QuestionService{
  
  private final QuestionRepository repository;
  private final QuestionMapper mapper;

  public String createQuestion (@Valid QuestionRequest request) {
    var Question = repository.save(mapper.toQuestion(request));
    return Question.getId();
  }

  public QuestionResponse findById(String questionId) {
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

  public QuestionResponse updateQuestionContent(String questionId, QuestionRequest questionRequest) {
    var question = repository.findById(questionId)
            .orElseThrow(() -> new EntityNotFoundException("Question not found with id: " + questionId));

    // Actualizar solo el contenido de la pregunta
    question.setContent(questionRequest.content());
    question.setLastUpdate(LocalDateTime.now());

    // Guardar los cambios
    repository.save(question);

    // Devolver la respuesta actualizada
    return mapper.toQuestionResponse(question);
}

  public void deleteCustomer(String questionId) {
    repository.deleteById(questionId);
  }

  public QuestionResponse addAnswerToQuestion(String questionId, Answer answerRequest) {
    var question = repository.findById(questionId)
            .orElseThrow(() -> new EntityNotFoundException("Question not found with id: " + questionId));

    //añadir fecha de creación a la respuesta
    answerRequest.setDate(LocalDateTime.now());
    // Añadir la respuesta a la lista de respuestas de la pregunta
    if(question.getAnswers() == null){
      question.setAnswers(new ArrayList<>());
    }
    question.getAnswers().add(answerRequest);
    question.setLastUpdate(LocalDateTime.now());

    // Guardar los cambios
    repository.save(question);

    // Devolver la respuesta actualizada
    return mapper.toQuestionResponse(question);
  }
}
