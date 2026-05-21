package user.service.dtos.requestest.auth.authLocal;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequest {
    @NotBlank(message = "Email is required")
    @Email
    @Schema(example = "example@example.com")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 32, message = "Password must be strong!")
    @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d).+$",
            message = "Password must contain at least 1 letter and 1 number"
    )
    @Schema(example = "string")
    private String password;

    @NotBlank(message = "Confirm Password is required")
    @Schema(example = "string")
    private String confirmPassword;
}
