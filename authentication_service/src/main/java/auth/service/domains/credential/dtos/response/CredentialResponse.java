package auth.service.domains.credential.dtos.response;

import auth.service.domains.role.dtos.response.RoleResponse;
import auth.service.enums.AuthProvider;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CredentialResponse {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private AuthProvider provider;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String providerUserId;

    @Builder.Default
    Set<RoleResponse> roles = new HashSet<>();
}
