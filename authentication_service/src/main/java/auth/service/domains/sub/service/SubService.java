package auth.service.domains.sub.service;

import auth.service.domains.sub.entity.Subscription;
import auth.service.domains.sub.repository.SubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubService {

    private final SubRepository subRepository;

    public void add(Subscription subscription) {
        subRepository.save(subscription);
    }

    public boolean existsByAppNameAndOrigin(String appName, String origin) {
        return subRepository.existsByAppNameAndOrigin(appName, origin);
    }

    public boolean existsByClientIdAndOrigin(String clientId, String origin) {
        return subRepository.existsByClientIdAndOrigin(clientId, origin);
    }

    public Subscription findByOriginAndClientId(String origin, String clientId) {
        return subRepository.findByOriginAndClientId(clientId, origin);
    }
}
