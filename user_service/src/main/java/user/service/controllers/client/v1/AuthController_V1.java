package user.service.controllers.client.v1;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import user.service.dtos.requestest.auth.authLocal.LoginRequest;
import user.service.dtos.requestest.auth.authLocal.RegisterRequest;
import user.service.dtos.requestest.auth.oauth2.AppleRequest;
import user.service.dtos.requestest.auth.oauth2.GitHubRequest;
import user.service.dtos.requestest.auth.oauth2.GoogleRequest;
import user.service.dtos.requestest.auth.oauth2.XRequest;
import user.service.dtos.requestest.user.UserCreateRequest;
import user.service.dtos.responses.ApiResponse;
import user.service.dtos.responses.UserResponse;
import user.service.services.client.v1.auth.AuthService;

@RestController
@RequestMapping("api/v1/auth")
@Slf4j
public class AuthController_V1 {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponse>> registerLocal(@RequestBody @Valid RegisterRequest body) {
        log.info("Register local is running...");
        System.out.println("registerLocal: " + body);
        return authService.registerLocal(body);
    }

    @PostMapping("/login")
    public void loginLocal(@RequestBody LoginRequest body) {
        System.out.println("Login Local: " + body);
    }

    @PostMapping("google")
    public void loginGoogle(@RequestBody GoogleRequest body) {
        System.out.println("Login Google" + body.getToken());
    }

    @PostMapping("apple")
    public void loginApple(@RequestBody AppleRequest body) {
        System.out.println("Login Apple" + body.getToken());
    }

    @PostMapping("gitbuh")
    public void loginGithub(@RequestBody GitHubRequest body) {
        System.out.println("Login Github" + body.getToken());
    }

    @PostMapping("x")
    public void loginX(@RequestBody XRequest body) {
        System.out.println("Login X" + body.getToken());
    }
}
