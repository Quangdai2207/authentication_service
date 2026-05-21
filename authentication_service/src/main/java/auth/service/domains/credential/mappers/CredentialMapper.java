package auth.service.domains.credential.mappers;

import auth.service.domains.credential.dtos.request.CredentialCreate;
import auth.service.domains.credential.dtos.response.CredentialResponse;
import auth.service.domains.credential.entity.Credential;

import java.util.HashSet;

public class CredentialMapper {

    public static CredentialResponse mapTo(Credential credential) {
        return CredentialResponse.builder()
                .provider(credential.getProvider())
                .providerUserId(credential.getProviderUserId())
                .roles(new HashSet<>())
                .build();
    }

    public static Credential mapTo(CredentialCreate body) {
        return Credential.builder()
                .provider(body.getProvider())
                .providerUserId(body.getProviderUserId())
                .passwordHash(body.getPasswordHash())
                .build();
    }
}
