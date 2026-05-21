package auth.service.domains.auth.dtos.request.oauth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
@ToString
public class GoogleRequest {

    @NotBlank(message = "tokenId is missing")
    private String tokenId;
}
