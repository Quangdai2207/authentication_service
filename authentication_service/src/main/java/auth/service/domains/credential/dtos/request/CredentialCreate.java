package auth.service.domains.credential.dtos.request;

import auth.service.enums.AuthProvider;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CredentialCreate {
    @NotBlank(message = "Provider is not empty")
    private AuthProvider provider;

    @NotBlank(message = "ProviderUserId is not empty")
    private String providerUserId;

    @NotBlank(message = "Password is not empty")
    private String passwordHash;
}
