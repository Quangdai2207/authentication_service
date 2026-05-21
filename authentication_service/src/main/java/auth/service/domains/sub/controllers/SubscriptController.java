package auth.service.domains.sub.controllers;

import auth.service.domains.sub.entity.Subscription;
import auth.service.domains.sub.service.SubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.util.Base64;
import java.util.UUID;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/auth-service")
public class SubscriptController {
    @Autowired
    private SubService subService;

    private static final Pattern DOMAIN_PATTERN =
            Pattern.compile(
                    "^(http://|https://)" +
                            "((localhost)|(([a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}))" +
                            "(:\\d+)?" +
                            "(/.*)?$"
            );

    @GetMapping("/subscription")
    public String subscription() {
        return "views/sub";
    }

    @PostMapping("/subscription")
    public String subscription(@RequestParam String appName, @RequestParam String domainName, Model model) {
        // validate app name
        if (appName == null || appName.isBlank()) {
            model.addAttribute("error", "Application name is required");
            return "views/sub";
        }

        // validate domain
        if (!DOMAIN_PATTERN.matcher(domainName).matches() || domainName.isBlank()) {
            model.addAttribute("error", "Domain must start with http:// or https://");
            return "views/sub";
        }

        boolean isExist = subService.existsByAppNameAndOrigin(appName, domainName);
        if (isExist) {
            model.addAttribute("error", "Sub already exists");
            return "views/sub";
        }

        try {

            // generate client id
            String clientId = UUID.randomUUID().toString();

            // generate RSA key pair
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(2048);

            KeyPair keyPair = generator.generateKeyPair();

            // Chuyen thanh PEM encode keys;
            String publicKey = Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
            String privateKey = Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());

            System.out.println(publicKey);
            System.out.println(privateKey);

            model.addAttribute("appName", appName);
            model.addAttribute("domainName", domainName);
            model.addAttribute("clientId", clientId);
            model.addAttribute("publicKey", publicKey);

            Subscription subscription = Subscription.builder()
                    .appName(appName)
                    .origin(domainName)
                    .clientId(clientId)
                    .privateKey(privateKey)
                    .publicKey(publicKey)
                    .build();
            subService.add(subscription);

            return "views/success";

        } catch (Exception e) {

            model.addAttribute("error", "Generate key failed");

            return "views/sub";
        }
    }
}