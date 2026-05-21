package auth.service.domains.credential_role.repository;

import auth.service.domains.credential.entity.Credential;
import auth.service.domains.credential_role.entity.CredentialRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CredentialRoleRepository extends JpaRepository<CredentialRole, UUID> {
    /// ? Tim role theo credentialId
    List<CredentialRole> findByCredential(String credentialId);

    //? Tim Role theo credential
    List<CredentialRole> findByCredential(Credential credential);
}
