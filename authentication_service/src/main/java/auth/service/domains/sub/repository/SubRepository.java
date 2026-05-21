package auth.service.domains.sub.repository;

import auth.service.domains.sub.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SubRepository extends JpaRepository<Subscription, UUID> {
    boolean existsByAppNameAndOrigin(String appName, String origin);
    boolean existsByClientIdAndOrigin(String clientId, String origin);
    Subscription findByOriginAndClientId(String clientId, String origin);
}
