package co.edu.javeriana.msc.turismo.service_rating_microservice.comment.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Validated
public class Customer {
    @NotNull(message = "The id of the user is required")
    private String id;
    @NotNull(message = "The user type is required")
    private String userType;
    @NotNull(message = "The username of the user is required")
    private String username;
    @NotNull(message = "The first name of the user is required")
    private String firstName;
    @NotNull(message = "The last name of the user is required")
    private String lastName;
    @NotNull(message = "The email of the user is required")
    private String email;
}
