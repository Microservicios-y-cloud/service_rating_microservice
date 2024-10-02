package co.edu.javeriana.msc.turismo.service_rating_microservice.question.controllers;

import co.edu.javeriana.msc.turismo.service_rating_microservice.question.model.Answer;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.javeriana.msc.turismo.service_rating_microservice.question.dto.QuestionRequest;
import co.edu.javeriana.msc.turismo.service_rating_microservice.question.dto.QuestionResponse;
import co.edu.javeriana.msc.turismo.service_rating_microservice.question.services.QuestionService;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/questions")
@RequiredArgsConstructor
public class QuestionController {
    
    private final QuestionService questionService;

    // Obtener una pregunta por ID
    @GetMapping("/{question-id}")
    public ResponseEntity<QuestionResponse> getQuestion(
            @PathVariable("question-id") String questionId) {
        return ResponseEntity.ok(questionService.findById(questionId));
    }

    // Obtener todas las preguntas
    @GetMapping
    public ResponseEntity<List<QuestionResponse>> findAll() {
        return ResponseEntity.ok(questionService.findAllQuestions());
    }

    // Crear una nueva pregunta
    @PostMapping
    public ResponseEntity<String> createQuestion(
            @Valid @RequestBody QuestionRequest questionRequest) {
        return ResponseEntity.ok(questionService.createQuestion(questionRequest));
    }

    // Actualizar el contenido de una pregunta existente
    @PutMapping("/{question-id}")
    public ResponseEntity<QuestionResponse> updateQuestionContent(
            @PathVariable("question-id") String questionId,
            @Valid @RequestBody QuestionRequest updateRequest) {
        QuestionResponse updatedQuestion = questionService.updateQuestionContent(questionId, updateRequest);
        return ResponseEntity.ok(updatedQuestion);
    }

    // Eliminar una pregunta por ID
    @DeleteMapping("/{question-id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable("question-id") String questionId) {
        questionService.deleteCustomer(questionId);
        return ResponseEntity.noContent().build();  // Devuelve 204 No Content
    }

    //añadir respuesta a pregunta
    @PostMapping("/{question-id}/answers")
    public ResponseEntity<QuestionResponse> addAnswerToQuestion(
            @PathVariable("question-id") String questionId,
            @Valid @RequestBody Answer answerRequest) {
        QuestionResponse updatedQuestion = questionService.addAnswerToQuestion(questionId, answerRequest);
        return ResponseEntity.ok(updatedQuestion);
    }

    //obtener todas las preguntas de un mismo serviceId
    @GetMapping("/service/{service-id}")
    public ResponseEntity<List<QuestionResponse>> findAllByServiceId(
            @PathVariable("service-id") Long serviceId) {
        return ResponseEntity.ok(questionService.findAllByServiceId(serviceId));
    }
}