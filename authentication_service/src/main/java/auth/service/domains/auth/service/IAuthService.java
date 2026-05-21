package auth.service.domains.auth.service;

import auth.service.domains.auth.dtos.request.local.RegisterRequest;
import auth.service.domains.auth.dtos.response.v1.AuthResponseV1;
import auth.service.response.v1.ApiResponseV1;
import org.springframework.http.ResponseEntity;

public interface IAuthService {
    ResponseEntity<ApiResponseV1<AuthResponseV1>> register(RegisterRequest body);
}
