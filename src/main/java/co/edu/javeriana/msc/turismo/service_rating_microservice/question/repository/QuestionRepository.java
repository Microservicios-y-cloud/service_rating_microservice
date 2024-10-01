package co.edu.javeriana.msc.turismo.service_rating_microservice.question.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import co.edu.javeriana.msc.turismo.service_rating_microservice.question.model.Question;

import java.util.Arrays;
import java.util.List;


@Repository
public interface QuestionRepository extends MongoRepository<Question, String> {
    List<Question> findAllByServiceId(Long serviceId);
}