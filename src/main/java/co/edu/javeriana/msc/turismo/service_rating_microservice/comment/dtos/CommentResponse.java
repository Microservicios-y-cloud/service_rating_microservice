package co.edu.javeriana.msc.turismo.service_rating_microservice.comment.dtos;

import co.edu.javeriana.msc.turismo.service_rating_microservice.comment.enums.Qualification;

public record CommentResponse(
    String id,
    Long serviceId,
    Qualification qualification,
    Customer createdBy,
    String content
) {
}