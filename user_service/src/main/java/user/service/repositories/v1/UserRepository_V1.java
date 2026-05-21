package user.service.repositories.v1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import user.service.entities.UserEntity;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository_V1 extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findByEmail(String email);
}
