package auth.service.domains.auth.controller.v1;

import auth.service.domains.auth.dtos.request.local.LoginRequest;
import auth.service.domains.auth.dtos.request.local.RegisterRequest;
import auth.service.domains.auth.dtos.request.local.ResendVerifyRequest;
import auth.service.domains.auth.dtos.response.v1.AuthResponseV1;
import auth.service.domains.auth.dtos.response.v1.LoginResponse;
import auth.service.domains.auth.service.AuthService;
import auth.service.response.v1.ApiResponseV1;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthControllerV1 {
    private final AuthService authService;

    /// Xac thuc bang phuong thuc LOCAL tren ung dung voi Login() va Register()
    @PostMapping("/login")
    public ResponseEntity<ApiResponseV1<LoginResponse>> login(
            @RequestBody @Valid LoginRequest body,
            @RequestHeader("X-Client-Id") String clientId,
            HttpServletRequest request
    ) {
        return authService.login(body, clientId, request);
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponseV1<AuthResponseV1>> register(
            @RequestBody @Valid RegisterRequest body,
            @RequestHeader("X-Client-Id") String clientId, // Dung @RequestHeader de render input header phia swagger UI
            HttpServletRequest request
    ) {
        try {
            return authService.register(body, request, clientId);
        } catch (MessagingException e) {
            log.error("Auth Controller error {}", e.getMessage());
        }
        return ApiResponseV1.internalServerError();
    }

    @GetMapping("/verify-account")
    public void verify_account(@RequestParam String token, HttpServletResponse response) throws IOException {
        authService.verify_email(token, response);
    }

    @PostMapping("/resend-verify")
    public ResponseEntity<ApiResponseV1<Object>> resendEmail(
            @RequestBody @Valid ResendVerifyRequest body
    ) {
        log.info("AuthController Layer - resendEmail() {}", body);
        try {
            String email = body.getEmail().toLowerCase().trim();
            System.out.println("Token: " + email);
            return authService.resendEmail(email);
        } catch (MessagingException e) {
            System.out.println("AuthController - Resend email: " + e.getMessage());
            return ApiResponseV1.badRequest();
        }
    }

    /// FE Call API alert-verify tai bat ky trang nao de thong bao cho nguoi dung xac thuc tai khoan thanh cong
    @GetMapping("/alert-verify")
    public ResponseEntity<ApiResponseV1<Object>> alertVerify() {
        return authService.alertVerify();
    }


    /// Xac thuc bang dich vu OAuth2 Client: Google, Apple, X, Github
    @PostMapping("/google")
    public void authWithGoogle() {
    }

    @PostMapping("/apple")
    public void authWithApple() {
    }

    @PostMapping("/github")
    public void authWithGithub() {
    }

    @PostMapping("/x")
    public void authWithX() {
    }
}
