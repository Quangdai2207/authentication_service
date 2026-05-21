package user.service.mappers.request;

import user.service.dtos.requestest.credential.CredentialCreateRequest;
import user.service.entities.Credential;

public class CredentialMapper {

    public static Credential mapTo(CredentialCreateRequest credentialDTO) {
        return Credential.builder()
                .passwordHash(credentialDTO.getPasswordHash())
                .provider(credentialDTO.getProvider())
                .providerUserId(credentialDTO.getProviderUserId())
                .user(credentialDTO.getUser())
                .build();
    }

    public static CredentialCreateRequest mapTo(Credential credentia) {
        return CredentialCreateRequest.builder()
                .passwordHash(credentia.getPasswordHash())
                .provider(credentia.getProvider())
                .providerUserId(credentia.getProviderUserId())
                .user(credentia.getUser())
                .build();
    }
}