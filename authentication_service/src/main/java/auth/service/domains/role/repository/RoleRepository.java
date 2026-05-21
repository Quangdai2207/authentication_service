package auth.service.domains.role.repository;

import auth.service.domains.role.entity.Role;
import auth.service.domains.role.enums.RoleAuthorization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByRole(RoleAuthorization role);

    boolean existsByRole(RoleAuthorization role);
}
