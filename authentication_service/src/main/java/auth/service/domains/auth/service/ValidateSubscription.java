package auth.service.domains.auth.service;

import auth.service.domains.sub.entity.Subscription;
import auth.service.domains.sub.service.SubService;
import auth.service.exceptions.BadRequestException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public class ValidateSubscription {
    public Subscription validateSubs(HttpServletRequest request, String clientId, SubService subService) {
        // Kiem tra Origin dang xac thuc co dang ky chua
        String origin = request.getHeader("Origin");
        if (clientId == null || clientId.isBlank()) throw new BadRequestException("X-Client-ID is null");
        // Check DB weather this origin is registered auth service with client-ID
        return subService.findByOriginAndClientId(clientId, origin);
    }
}
