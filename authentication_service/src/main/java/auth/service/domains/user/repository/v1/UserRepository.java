package auth.service.domains.user.repository.v1;

import auth.service.domains.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/// <h4 style="color: white">Trong Repository, su dung pattern JQL - method query pattern de truy van du lieu thay vi dung HQL</h4>
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);

    Optional<User> findByFirstNameAndLastName(String firstName, String lastName);

    Optional<User> findById(UUID id);

    boolean existsByEmail(String email);

    boolean existsByFirstNameAndLastName(String firstName, String lastName);
}
