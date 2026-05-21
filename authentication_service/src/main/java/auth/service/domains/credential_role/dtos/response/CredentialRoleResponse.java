package auth.service.domains.credential_role.dtos.response;

import auth.service.domains.credential.dtos.response.CredentialResponse;
import auth.service.domains.role.dtos.response.RoleResponse;
import com.fasterxml.jackson.annotation.JsonInclude;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CredentialRoleResponse {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("role")
    private RoleResponse roleResponse;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("credential")
    private CredentialResponse credentialResponse;
}
