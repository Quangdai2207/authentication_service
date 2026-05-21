package auth.service.domains.auth.dtos.request.local;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResendVerifyRequest {

    @Schema(example = "email@example.com")
    @NotBlank(message = "Email is required")
    @Email
    private String email;
}
