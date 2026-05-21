package auth.service.domains.auth.dtos.request.oauth;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class GithubRequest {

    @NotBlank(message = "tokenId is missing")
    private String tokenId;
}
