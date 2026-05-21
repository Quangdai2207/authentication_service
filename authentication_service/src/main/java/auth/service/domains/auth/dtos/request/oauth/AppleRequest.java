package auth.service.domains.auth.dtos.request.oauth;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AppleRequest {
    @NotBlank(message = "Token is missing")
    private String token;
}
