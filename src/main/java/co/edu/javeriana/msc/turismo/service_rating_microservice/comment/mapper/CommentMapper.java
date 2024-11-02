package co.edu.javeriana.msc.turismo.service_rating_microservice.comment.mapper;

import co.edu.javeriana.msc.turismo.service_rating_microservice.comment.dtos.CommentRequest;
import co.edu.javeriana.msc.turismo.service_rating_microservice.comment.dtos.CommentResponse;
import co.edu.javeriana.msc.turismo.service_rating_microservice.comment.model.Comment;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

@Service

public class CommentMapper {
    public Comment toComment(CommentRequest request) {
        return Comment.builder()
                .id(request.id())
                .serviceId(request.serviceId())
                .qualification(request.qualification())
                .createdBy(request.createdBy())
                .content(request.content())
                .date(LocalDateTime.now())
                .build();
    }

    public CommentResponse toCommentResponse(Comment comment) {
        return new CommentResponse(
                        comment.getId(),
                        comment.getServiceId(),
                        comment.getQualification(),
                        comment.getCreatedBy(),
                        comment.getContent(),
                        comment.getDate()
                );
    }
}
