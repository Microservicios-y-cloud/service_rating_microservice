package co.edu.javeriana.msc.turismo.service_query_microservice.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "locations")
public record LocationResponse(
        @Id
        Long id,
        String address,
        Double latitude,
        Double longitude,
        String country,
        String city,
        String municipality
) {
}
