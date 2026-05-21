package user.service.repositories.v1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import user.service.entities.CredentialRole;

import java.util.UUID;

@Repository
public interface CredentialRoleRepository_V1 extends JpaRepository<CredentialRole, UUID> {
}
