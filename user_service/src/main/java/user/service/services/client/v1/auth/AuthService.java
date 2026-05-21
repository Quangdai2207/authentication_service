package user.service.services.client.v1.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import user.service.dtos.requestest.auth.authLocal.RegisterRequest;
import user.service.dtos.requestest.credential.CredentialCreateRequest;
import user.service.dtos.requestest.user.UserCreateRequest;
import user.service.dtos.responses.ApiResponse;
import user.service.dtos.responses.CredentialResponse;
import user.service.dtos.responses.UserResponse;
import user.service.entities.Credential;
import user.service.entities.CredentialRole;
import user.service.entities.Role;
import user.service.entities.UserEntity;
import user.service.enums.AuthProvider;
import user.service.enums.RoleBaseAuthorized;
import user.service.exception.BadRequestException;
import user.service.mappers.request.CredentialMapper;
import user.service.mappers.request.UserMapper;
import user.service.mappers.response.ResponseCredentialMapper;
import user.service.mappers.response.ResponseUserMapper;
import user.service.repositories.v1.CredentialRepository_V1;
import user.service.repositories.v1.CredentialRoleRepository_V1;
import user.service.repositories.v1.RoleRepository_V1;
import user.service.repositories.v1.UserRepository_V1;

@Service
@Slf4j
public class AuthService {
    private final PasswordEncoder encoder;
    private final UserRepository_V1 userRepository_V1;
    private final RoleRepository_V1 roleRepository_V1;
    private final CredentialRoleRepository_V1 credentialRoleRepository_V1;
    private final CredentialRepository_V1 credentialRepository_V1;

    @Autowired
    public AuthService(
            UserRepository_V1 userRepository_V1,
            PasswordEncoder encoder,
            RoleRepository_V1 roleRepository_V1,
            CredentialRoleRepository_V1 credentialRoleRepository_V1,
            CredentialRepository_V1 credentialRepository_V1) {
        this.userRepository_V1 = userRepository_V1;
        this.encoder = encoder;
        this.roleRepository_V1 = roleRepository_V1;
        this.credentialRoleRepository_V1 = credentialRoleRepository_V1;
        this.credentialRepository_V1 = credentialRepository_V1;
    }

    public void CheckCredential() {
    }

    /// Dung chung cho chuc nang xac thuc Local cua ung dung
    public void authWithLocal() {

    }

    /// Dung de goi cac phuong thuc xac thuc bang Google, Apple, X, Github
    public void withOauth() {
    }

    public void loginLocal() {

    }

    @Transactional
    public ResponseEntity<ApiResponse<UserResponse>> registerLocal(RegisterRequest body) {
        log.info("Registering local user");

        String email = body.getEmail().toLowerCase();

        /// Kiem tra email nguoi dung da ton tai va duoc cap boi Local hay tu OAuth
        /// Neu nhu Nguoi dung dang ky xac thuc bang OAuth trung voi tai khoan email ma nguoi dung dang ky xac thuc LOCAL
        /// thi van duoc phep, luc nay them tinh nang link tai khoan cho nguoi dung.
        if (credentialRepository_V1.existsByProviderAndProviderUserId(AuthProvider.LOCAL, email))
            throw new BadRequestException("Email already exists");

        if (!body.getPassword().equals(body.getConfirmPassword()))
            throw new BadRequestException("Passwords do not match");

        /// Tao User
        UserEntity user = UserEntity.builder().email(body.getEmail()).build();
        userRepository_V1.save(user); ///? Save database

        /// Tao Credential
        /// Neu nguoi dung dang ky tai khoan de xac thuc bang LOCAL cua ung dung, providerUserId phai luu bang username/email
        CredentialCreateRequest credentialDTO = CredentialCreateRequest.builder()
                .provider(AuthProvider.LOCAL)
                .providerUserId(email) /// Lay email nguoi dung lam providerUserId khi nguoi dung su dung xac thuc LOCAL cua ung dung
                .passwordHash(encoder.encode(body.getPassword()))
                .user(user)
                .build();

        Credential credential = CredentialMapper.mapTo(credentialDTO);
        credentialRepository_V1.save(credential); ///? Save database

        /// Lay role tu DB, neu role chua co thi tao moi role
        Role role = roleRepository_V1.findByName(RoleBaseAuthorized.ROLE_USER).orElseGet(() ->
                roleRepository_V1.save(Role.builder()
                        .name(RoleBaseAuthorized.ROLE_USER)
                        .build())
        );

        /// tao CredentialRole
        CredentialRole credentialRole = CredentialRole.builder()
                .role(role)
                .credential(credential)
                .build();

        credentialRoleRepository_V1.save(credentialRole);

        /// Thiet lap du lieu DTO tra response
        UserResponse userResponse = ResponseUserMapper.mapTo(user);
        CredentialResponse credentialResponse = ResponseCredentialMapper.mapTo(credential);

        userResponse.getCredentials().add(credentialResponse);
        return ApiResponse.ok(userResponse);
    }

    public void authWithGoogle() {
    }

    public void authWithApple() {
    }

    public void authWithX() {
    }

    public void authWithGithub() {
    }

    /// Lay ho so nguoi dung va nguoi dung trong phien truy cap
    public void profile() {
    }

    /// Thay doi/ cap nhat mat khau khi nguoi dung trong phien truy cap
    public void changePassword() {
    }

    /// Cai dat mat khau khi nguoi dung quen mat khau va chua co phien truy cap
    public void resetPassword() {
    }

    public void verify(String token) {
    }


}
