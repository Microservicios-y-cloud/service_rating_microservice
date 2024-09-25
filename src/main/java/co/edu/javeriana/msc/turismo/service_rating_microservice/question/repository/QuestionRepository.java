package co.edu.javeriana.msc.turismo.service_rating_microservice.question.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import co.edu.javeriana.msc.turismo.service_rating_microservice.question.model.Question;


@Repository
public interface QuestionRepository extends MongoRepository<Question, String> {
}