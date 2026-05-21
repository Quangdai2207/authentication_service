package user.service.repositories.v1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import user.service.entities.Role;
import user.service.entities.UserEntity;
import user.service.enums.RoleBaseAuthorized;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository_V1 extends JpaRepository<Role, Integer> {

    Optional<Role> findByName(RoleBaseAuthorized role);
    boolean existsByName(RoleBaseAuthorized role);
}
