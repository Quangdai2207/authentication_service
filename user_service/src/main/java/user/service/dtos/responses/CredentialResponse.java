package user.service.dtos.responses;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import user.service.entities.Role;
import user.service.enums.AuthProvider;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CredentialResponse {
    private String providerUserId;

    @Enumerated(EnumType.STRING)
    private AuthProvider authProvider;
}
