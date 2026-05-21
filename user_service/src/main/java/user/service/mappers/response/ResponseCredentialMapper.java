package user.service.mappers.response;

import user.service.dtos.responses.CredentialResponse;
import user.service.entities.Credential;

public class ResponseCredentialMapper {

    public static CredentialResponse mapTo(Credential credential) {
        return CredentialResponse.builder()
                .providerUserId(credential.getProviderUserId())
                .authProvider(credential.getProvider())
                .build();
    }
}
