package auth.service.domains.auth.dtos.request.local;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
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
