package co.edu.javeriana.msc.turismo.service_rating_microservice.comment.controllers;

import co.edu.javeriana.msc.turismo.service_rating_microservice.comment.dtos.CommentRequest;
import co.edu.javeriana.msc.turismo.service_rating_microservice.comment.dtos.CommentResponse;
import co.edu.javeriana.msc.turismo.service_rating_microservice.comment.services.CommentService;
import co.edu.javeriana.msc.turismo.service_rating_microservice.question.model.Answer;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // Obtener un comentario por ID
    @GetMapping("/{comment-id}")
    public ResponseEntity<CommentResponse> getComment(
            @PathVariable("comment-id") String commentId) {
        return ResponseEntity.ok(commentService.findById(commentId));
    }

    // Crear un nuevo comentario
    @PostMapping
    public ResponseEntity<String> createComment(
            @Valid @RequestBody CommentRequest commentRequest) {
        return ResponseEntity.ok(commentService.createComment(commentRequest));
    }

    // Actualizar el contenido de un comentario existente
    @PutMapping("/{comment-id}")
    public ResponseEntity<CommentResponse> updateCommentContent(
            @PathVariable("comment-id") String commentId,
            @Valid @RequestBody CommentRequest updateRequest) {
        CommentResponse updatedComment = commentService.updateComment(commentId, updateRequest);
        return ResponseEntity.ok(updatedComment);
    }

    // Eliminar un comentario por ID
    @DeleteMapping("/{comment-id}")
    public ResponseEntity<Void> deleteComment(@PathVariable("comment-id") String commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build();  // Devuelve 204 No Content
    }
}