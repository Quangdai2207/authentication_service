package user.service.dtos.requestest.credential;

import jakarta.annotation.Nullable;
import lombok.*;
import user.service.entities.UserEntity;
import user.service.enums.AuthProvider;

import java.time.Instant;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CredentialCreateRequest {
    private AuthProvider provider;
    /// Neu nguoi dung xac thuc bang Local tren ung dung thi providerUserId co the lay Id cua User hoac khoi
    /// mot providerId khac cho user hoac co the la null
    private String providerUserId;

    @Nullable
    private String passwordHash;

    @Nullable
    private Instant lockedUntil;

    @Nullable
    private Instant passwordChangedAt;

    @Nullable
    private Instant lastLoginAt;

    private UserEntity user;
}
