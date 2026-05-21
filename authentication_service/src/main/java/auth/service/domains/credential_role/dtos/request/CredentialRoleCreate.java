package auth.service.domains.credential_role.dtos.request;

import auth.service.domains.credential.entity.Credential;
import auth.service.domains.role.entity.Role;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CredentialRoleCreate {
    @NotBlank(message = "Role is missing")
    private Role role;

    @NotBlank(message = "Credential is missing")
    private Credential credential;
}
