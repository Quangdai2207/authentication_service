package user.service.repositories.v1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import user.service.entities.Credential;
import user.service.enums.AuthProvider;

import java.util.UUID;

@Repository
public interface CredentialRepository_V1 extends JpaRepository<Credential, UUID> {
    boolean existsByProviderAndProviderUserId(AuthProvider provider, String providerUserId);
}
