package auth.service.domains.auth.service;

import auth.service.domains.auth.dtos.request.local.LoginRequest;
import auth.service.domains.auth.dtos.request.local.RegisterRequest;
import auth.service.domains.auth.dtos.request.oauth.AppleRequest;
import auth.service.domains.auth.dtos.request.oauth.GithubRequest;
import auth.service.domains.auth.dtos.request.oauth.GoogleRequest;
import auth.service.domains.auth.dtos.request.oauth.XRequest;
import auth.service.domains.auth.dtos.response.v1.AuthResponseV1;
import auth.service.domains.auth.dtos.response.v1.LoginResponse;
import auth.service.response.v1.ApiResponseV1;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

public interface IAuthService {

    public void authWithGoogle(GoogleRequest googleRequest);

    public void authWithApple(AppleRequest appleRequest);

    public void authWithX(XRequest xRequest);

    public void authWithGithub(GithubRequest githubRequest);

    ResponseEntity<ApiResponseV1<AuthResponseV1>> register(RegisterRequest body, HttpServletRequest request, String clientId) throws MessagingException;

    public ResponseEntity<ApiResponseV1<LoginResponse>> login(LoginRequest body, String clientId, HttpServletRequest request);
}
