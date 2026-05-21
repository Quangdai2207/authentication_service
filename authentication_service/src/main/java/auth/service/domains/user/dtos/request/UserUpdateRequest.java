package auth.service.domains.user.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserUpdateRequest {
    @Schema(example = "email@example.com")
    @NotBlank(message = "Email is required")
    @Email
    private String email;

    @Schema(example = "string")
    @NotBlank(message = "FirstName is required")
    @Pattern(
            regexp = "^([A-Za-z])+$",
            message = "firstName must only contain characters"
    )
    private String firstName;

    @Schema(example = "string")
    @NotBlank(message = "LastName is required")
    @Pattern(
            regexp = "^([A-Za-z])+$",
            message = "lastName must only contain characters"
    )
    private String lastName;
}
