package co.edu.javeriana.msc.turismo.service_rating_microservice.question.controllers;

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
            @PathVariable("question-id") Long questionId) {
        return ResponseEntity.ok(questionService.findById(questionId));
    }

    // Obtener todas las preguntas
    @GetMapping
    public ResponseEntity<List<QuestionResponse>> findAll() {
        return ResponseEntity.ok(questionService.findAllQuestions());
    }

    // Crear una nueva pregunta
    @PostMapping
    public ResponseEntity<Long> createQuestion(
            @Valid @RequestBody QuestionRequest questionRequest) {
        Long createdQuestionId = questionService.createQuestion(questionRequest);
        return new ResponseEntity<>(createdQuestionId, HttpStatus.CREATED);
    }

    // Actualizar el contenido de una pregunta existente
    @PutMapping("/{question-id}")
    public ResponseEntity<QuestionResponse> updateQuestionContent(
            @PathVariable("question-id") Long questionId,
            @Valid @RequestBody QuestionRequest updateRequest) {
        QuestionResponse updatedQuestion = questionService.updateQuestionContent(questionId, updateRequest);
        return ResponseEntity.ok(updatedQuestion);
    }

    // Eliminar una pregunta por ID
    @DeleteMapping("/{question-id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable("question-id") Long questionId) {
        questionService.deleteCustomer(questionId);
        return ResponseEntity.noContent().build();  // Devuelve 204 No Content
    }
}