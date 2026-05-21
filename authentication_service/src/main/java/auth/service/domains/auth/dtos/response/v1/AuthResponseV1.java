package auth.service.domains.auth.dtos.response.v1;

import auth.service.domains.credential.dtos.response.CredentialResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponseV1 {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String email;

    @JsonProperty("credential")
    private CredentialResponse credentialResponse;
}
