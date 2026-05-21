package auth.service.domains.credential.repository;

import auth.service.domains.credential.entity.Credential;
import auth.service.domains.user.entity.User;
import auth.service.enums.AuthProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CredentialRepository extends JpaRepository<Credential, UUID> {
    boolean existsByProviderAndProviderUserId(AuthProvider authProvider, String email);

    Credential findByUser(User user);

    Credential findByUserAndProvider(User user, AuthProvider authProvider);
}
