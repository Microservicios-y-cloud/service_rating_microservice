package co.edu.javeriana.msc.turismo.service_rating_microservice.comment.services;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import co.edu.javeriana.msc.turismo.service_rating_microservice.comment.dtos.CommentRequest;
import co.edu.javeriana.msc.turismo.service_rating_microservice.comment.dtos.CommentResponse;
import co.edu.javeriana.msc.turismo.service_rating_microservice.comment.mapper.CommentMapper;
import co.edu.javeriana.msc.turismo.service_rating_microservice.comment.model.Comment;
import co.edu.javeriana.msc.turismo.service_rating_microservice.comment.repository.CommentRepository;
import co.edu.javeriana.msc.turismo.service_rating_microservice.comment.repository.HistoricoServiciosRepository;
import co.edu.javeriana.msc.turismo.service_rating_microservice.queue.repository.SuperServiceRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class CommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final SuperServiceRepository superServiceRepository;
    private final HistoricoServiciosRepository historicoServiciosRepository;

    public String createComment(CommentRequest request) {
        var historico = historicoServiciosRepository.findAllByUserId(request.createdBy().getId());
        if(!superServiceRepository.existsById(request.serviceId()) && historico.isEmpty()){
            throw new EntityNotFoundException("Service not found with id: " + request.serviceId() + "Or user not found with id: " + request.createdBy().getId());
        }
        Comment comment = commentMapper.toComment(request);
        comment.setDate(LocalDateTime.now());
        Comment savedComment = commentRepository.save(comment);
        return savedComment.getId();
    }

    public List<CommentResponse> getCommentsByServiceId(Long serviceId) {
        return commentRepository.findAllByServiceId(serviceId)
                .stream()
                .map(commentMapper::toCommentResponse)
                .collect(Collectors.toList());
    }

    public CommentResponse updateComment(String id, CommentRequest request) {
        var historico = historicoServiciosRepository.findAllByUserId(request.createdBy().getId());
        if(!superServiceRepository.existsById(request.serviceId()) && historico.isEmpty()){
            throw new EntityNotFoundException("Service not found with id: " + request.serviceId() + "Or user not found with id: " + request.createdBy().getId());
        }
        Comment comment = commentRepository.findById(id)
                .orElseThrow();
        comment.setQualification(request.qualification());
        comment.setContent(request.content());
        Comment updatedComment = commentRepository.save(comment);
        return commentMapper.toCommentResponse(updatedComment);
    }

    public void deleteComment(String id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow();
        commentRepository.delete(comment);
    }

    public List<CommentResponse> findAllByServiceId(Long serviceId) {
    return commentRepository.findAllByServiceId(serviceId)
            .stream()
            .map(commentMapper::toCommentResponse)
            .collect(Collectors.toList());
    }

    public List<CommentResponse> getCommentsByCustomerId(String customerId) {
        return commentRepository.findAll()
                .stream()
                .filter(comment -> comment.getCreatedBy().getId().equals(customerId))
                .map(commentMapper::toCommentResponse)
                .collect(Collectors.toList());
    }

    public CommentResponse findById(String commentId) {
        return commentRepository.findById(commentId)
                .map(commentMapper::toCommentResponse)
                .orElseThrow();
        
    }
}
