package co.edu.javeriana.msc.turismo.service_rating_microservice.comment.dtos;

import co.edu.javeriana.msc.turismo.service_rating_microservice.comment.enums.Qualification;
import jakarta.validation.constraints.NotNull;

public record CommentRequest(
        String id,
        @NotNull(message = "The service id of the comment is required")
        Long serviceId,
        @NotNull(message = "The qualification of the comment is required")
        Qualification qualification,
        @NotNull(message = "The user of the comment is required")
        Customer createdBy,
        String content
) {
}