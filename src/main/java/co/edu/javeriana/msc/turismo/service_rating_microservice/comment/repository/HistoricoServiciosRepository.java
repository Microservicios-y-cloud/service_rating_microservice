package co.edu.javeriana.msc.turismo.service_rating_microservice.comment.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import co.edu.javeriana.msc.turismo.service_rating_microservice.comment.model.Comment;
import co.edu.javeriana.msc.turismo.service_rating_microservice.comment.model.HistoricoServicios;

import java.util.List;

@Repository

public interface HistoricoServiciosRepository extends MongoRepository<HistoricoServicios, String> {
    List<HistoricoServicios> findAllByUserId(String userId);
}
