package auth.service.domains.auth.service;

import auth.service.configs.jwt.JwtConfig;
import auth.service.domains.auth.dtos.request.local.LoginRequest;
import auth.service.domains.auth.dtos.request.local.RegisterRequest;
import auth.service.domains.auth.dtos.request.oauth.AppleRequest;
import auth.service.domains.auth.dtos.request.oauth.GithubRequest;
import auth.service.domains.auth.dtos.request.oauth.GoogleRequest;
import auth.service.domains.auth.dtos.request.oauth.XRequest;
import auth.service.domains.auth.dtos.response.v1.AuthResponseV1;
import auth.service.domains.auth.dtos.response.v1.LoginResponse;
import auth.service.domains.credential.dtos.response.CredentialResponse;
import auth.service.domains.credential.entity.Credential;
import auth.service.domains.credential.mappers.CredentialMapper;
import auth.service.domains.credential.repository.CredentialRepository;
import auth.service.domains.credential_role.entity.CredentialRole;
import auth.service.domains.credential_role.repository.CredentialRoleRepository;
import auth.service.domains.role.dtos.response.RoleResponse;
import auth.service.domains.role.entity.Role;
import auth.service.domains.role.enums.RoleAuthorization;
import auth.service.domains.role.mappers.RoleMapper;
import auth.service.domains.role.repository.RoleRepository;
import auth.service.domains.sub.entity.Subscription;
import auth.service.domains.sub.service.SubService;
import auth.service.domains.user.entity.User;
import auth.service.domains.user.repository.v1.UserRepository;
import auth.service.enums.AuthProvider;
import auth.service.exceptions.AuthenticationExceptionHandler;
import auth.service.exceptions.BadRequestException;
import auth.service.response.v1.ApiResponseV1;
import auth.service.utilities.EmailUtil;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestHeader;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService implements IAuthService {
    private final static int expire = 5;

    private final UserRepository userRepository;
    private final CredentialRepository credentialRepository;
    private final RoleRepository roleRepository;
    private final CredentialRoleRepository credentialRoleRepository;

    private final PasswordEncoder encoder;
    private final RedisTemplate<String, String> redisTemplate;
    private final EmailUtil emailUtil;
    private final JwtConfig jwtConfig;
    private final SubService subService;
    private final ValidateSubscription validateSubscription;

    @Transactional
    @Override
    public ResponseEntity<ApiResponseV1<AuthResponseV1>> register(
            RegisterRequest body,
            HttpServletRequest request,
            String clientId
    ) throws MessagingException {
        System.out.println("Register request: " + body.toString());
        log.info("AuthService is running: ");

        // Kiem tra da da dang ky dich vu Auth Servce chua
        Subscription sub = validateSubscription.validateSubs(request, clientId, subService);
        if (sub == null) throw new BadRequestException("This origin dose not registered");

        String email = body.getEmail().trim().toLowerCase();
        // Kiem tra credential nguoi dung da dang ky chua thay vi kiem tra email user table.
        boolean isExist = credentialRepository.existsByProviderAndProviderUserId(AuthProvider.LOCAL, email);
        if (isExist) throw new BadRequestException("Email already exists");

        // Kiem tra password va confirmPassword match khong
        if (!body.getPassword().equals(body.getConfirmPassword()))
            throw new BadRequestException("Passwords do not match");

        // Tao moi nguoi dung
        User user = User.builder().email(email).build();
        userRepository.save(user); // Save SB

        // Tao moi Credential
        Credential credential = Credential.builder()
                .provider(AuthProvider.LOCAL)
                .providerUserId(email)
                .user(user)
                .passwordHash(encoder.encode(body.getPassword())).build();
        credentialRepository.save(credential); // Save DB

        // Lay role User mac dinh gan cho nguoi dung pho thong
        Role role = roleRepository.findByRole(RoleAuthorization.ROLE_USER);

        CredentialRole credentialRepository = CredentialRole
                .builder()
                .role(role)
                .credential(credential)
                .build();
        credentialRoleRepository.save(credentialRepository); // swve DB

        // Gui email nguoi dung xac thuc email kich hoat tai khoan
        String token = UUID.randomUUID().toString();
        emailUtil.sendEmail_II(email, "Verify your account", token);

        System.out.println("Email sent");
        // Thuc hien luu token vao redis sau khi gui mail cho nguoi dung thanh cong
        redisTemplate.opsForValue().set("verify:" + token, email, Duration.ofMinutes(expire)); // luu token 5 phut

        // Chuyen doi DTO tra du lieu sau khi Register LOCAL thanh cong
        CredentialResponse credentialResponse = CredentialMapper.mapTo(credential);
        RoleResponse roleResponse = RoleMapper.mapTo(role);
        credentialResponse.getRoles().add(roleResponse);

        AuthResponseV1 authResponse = AuthResponseV1.builder().email(email).credentialResponse(credentialResponse).build();

        return ApiResponseV1.created(authResponse);
    }

    ///  XAC THUC EMAIL DE KICH HOAT TAI KHOAN NGUOI DUNG <br />
    /// Khi nguoi dung vao email click vao Link xac thuc email duoc gui sau khi nguoi dung dang ky thanh cong,
    /// <br/>
    /// Day la chuc nang ma nguoi dung thuc hien truc tiep ngay tren giao dien cua Google va FE hoan toan khong the can
    /// thiep truc tiep vao chuc nang nay de xu ly. Ngay khi nguoi dung vao email va click vao link xac thuc email, neu viec
    /// xac thuc that bai thi BE can phai dieu huong nguoi dung ve UI voi routerDom http://localhost:3000/auth/verify-fail
    /// ma FE phai dinh nghia truoc, dong thoi phai co input nhap email va button submit de nguoi dung co the thuc hien lai yeu
    /// cau xac thuc tu ung dung.
    public void verify_email(String token, HttpServletResponse response) throws IOException {
        System.out.println("Layer Auth Service: " + token);

        // lay email da luu trong redis kem voi token xac thuc tai khoan email
        String email = redisTemplate.opsForValue().get("verify:" + token);
        if (email == null) {
            // Neu email null nghia la token luu trong redis het han 5 phut, server redirect ve UI FE
            // FE phai tao UI xac thuc that bai va mot routerDom de server auto redirect ve UI do,
            // va co button gui lai token xac thuc.
            response.sendRedirect("http://localhost:3000/auth/veridy-fail");
            return;
        }

        User user = userRepository.findByEmail(email).orElse(null);
        Credential credential = credentialRepository.findByUserAndProvider(user, AuthProvider.LOCAL);
        // Kiem tra tai khoan da duoc kich hoat chua
        if (!credential.isEnabled()) {

            //! Delete token truoc khi kich hoat tai khoan
            redisTemplate.delete("verify:" + token);

            // Kich hoat tai khoan
            credential.setEnabled(true);
            credential.setFailedAttempts(0); // set gia tri 0 cho trang thai tai khoan chua co hanh vi co truy cap lan nao
            // Cap nhat du lieu
            credentialRepository.save(credential);
            // Dieu huong ve UI login FE sau khi kich hoat thanh cong
            response.sendRedirect("http://localhost:3000/auth/login");
            return;
        }
        System.out.println("Test luong kkhi nguoi dung co tinh vao lai email click lick xac thuc");
        response.sendRedirect("http://localhost:3000/auth/verify-fail");
    }

    /// Method resendEmail() thuc hien logic cho chuc nang gui lai link xac thuc email cua nguoi dung khi token het han,
    /// sai hoac da kich hoat roi.<br />
    /// Mot so tuong hop luu y trong chuc nang gui lai link xac thuc cho nguoi dung qua email nhu sau:<br /><br/>
    ///     1. Nguoi dung chua kich hoat do kich hoat cham va token het han <br />
    ///        Truong hop nay dac biet la nguoi dung click truc tiep tren giao dien hop thu Google nen FE khong the can thiep dieu
    ///        huong truc tiep, thay vao do BE dieu huong truc tiep nguoi dung ve UI cua FE voi routerDom
    ///        http://localhost:3000/auth/verify-fail de nguoi dung biet viec xac thuc email that bai.FE phai tao mot UI tuong ung
    ///        va routerDom http://localhost:3000/auth/verify-fail voi input email va button submit de nguoi dung thuc hien chuc
    ///        nang yeu cau gui lai xac thuc email. <br /><br/>
    ///     2. Nguoi dung co tinh sua token khong khop voi token trong Redis <br />
    ///        FE hien thi thong bao loi cho nguoi dung tai UI RouterDom http://localhost:3000/auth/verify-fail <br /><br />
    ///     3. Nguoi dung kich hoat thanh cong nhung co tinh vao email de kich hoat lan nua.<br />
    ///        truong hop nay kiem trra Credential ua user da duoc kich hoat chua, neu kich hoat roi thi reponse thong bao
    ///        cho FE va va FE tu dieu huong nguoi dung ve UI routerDom http://localhost30/auth/already-verify de nguoi dung
    ///        biet duoc tai khoan cua minh da duoc kich hoat thay vi FE dieu huong truc tiep ve trang register ma nguoi dung
    ///        chua biet duoc tai khoan da kich hoat hay chua.<br /><br/>
    ///     4. Nguoi dung co tinh click vao nut submit yeu cau gui lai xac thuc nhieu lan, BE can phai thuc hien ngan hanh dong nay
    ///        tu nguoi dung.
    @Transactional
    public ResponseEntity<ApiResponseV1<Object>> resendEmail(String email) throws MessagingException {

        User user = userRepository.findByEmail(email).orElse(null);

        // Kiem tra nguoi dung co dung email da dang ky truoc do hay email khac chua dang ky
        if (user == null) {
            return ApiResponseV1.notFound("Email not found");
        }

        // Kiem tra Credential nguoi dung da co trong du lieu chua
        Credential credential = credentialRepository.findByUserAndProvider(user, AuthProvider.LOCAL);

        // Neu Credentiual nguoi dung chua co trong DB gui phan hoi yeu cau nguoi dung Register LOCAL
        if (credential == null) {
            return ApiResponseV1.notFound("Email not found");
        }

        // Kiem tra Credential nguoi dung da duoc kich hoat chua, neu kich hoat roi tra respons va FE dieu huong
        // nguoi dung ve UI thong bao tai khoan da duoc kich hoat thay vi dieu huong ve UI register.
        if (credential.isEnabled()) {
            return ApiResponseV1.badRequest("Account already verified");
        }

        // Kiem tra redis da co email nay gui yeu cau xac thua lai email chua
        // Dieu nay tranh viet nguoi dung click nhieu lan vao button submit.
        if (Boolean.TRUE.equals(redisTemplate.hasKey("verify:cooldown:" + email)))
            return ApiResponseV1.badRequest("Your require was sent your email, recheck anh verify now.");

        // Tao token moi
        String token = UUID.randomUUID().toString();

        // verify:{token} -> email
        redisTemplate.opsForValue().set("verify:" + token, email, Duration.ofMinutes(5));

        // cooldown resend 60s
        redisTemplate.opsForValue().set("verify:cooldown:" + email, "1", Duration.ofMinutes(expire));

        emailUtil.sendEmail_II(email, "Verify your email", token);

        return ApiResponseV1.ok("Verification email sent");
    }

    /// Phuong thuc gui thong bao xac thuc thanh cong khi BE dieu huong nguoi dung ve UI login FE
    /// tai UI LoginPage, FE co the fetch api thong bao de render thong tin xac thuc thanh cong cho nguoi dung
    public ResponseEntity<ApiResponseV1<Object>> alertVerify() {
        return ApiResponseV1.ok("Verify successfully, login your email validated");
    }

    ///  Xac thuc bang LOCAL ung dung
    @Transactional
    @Override
    public ResponseEntity<ApiResponseV1<LoginResponse>> login(
            LoginRequest loginRequest,
            String clientId,
            HttpServletRequest request
    ) {
        // Xac thuc origin da dang ky dich vu auth service chua:
        Subscription sub = validateSubscription.validateSubs(request, clientId, subService);
        if (sub == null) throw new BadRequestException("This origin dose not registered");

        String email = loginRequest.getEmail().toLowerCase().trim();            // Lay email tu request body
        User user = userRepository.findByEmail(email).orElse(null);       // Lay user bang email

        // Kiem tra nguoi dung da chon chung thuc thuc dang nao de truy cap: LOCAL, GOOGLE, GITHUB
        Credential credential = credentialRepository.findByUserAndProvider(user, AuthProvider.LOCAL);
        if (credential == null) throw new AuthenticationExceptionHandler("Invalid credential");

        // Kiem tra nguoi dung da xac thuc email va kich hoat tai khoan chua
        if (!credential.isEnabled())
            throw new BadRequestException("Your account mot yet enable, access your email to verify for enable");

        // Compares hash password
        boolean isPassLegal = BCrypt.checkpw(loginRequest.getPassword(), credential.getPasswordHash());
        if (!isPassLegal) throw new BadRequestException("Your account password is incorrect");

        // Lay cac role cua user
        List<Role> roles = credentialRoleRepository
                .findByCredential(credential)
                .stream()
                .map(CredentialRole::getRole)
                .toList();

        roles.forEach(role -> {
            System.out.println(role.getRole().getName());
        });

        // Tao Token
        String token = jwtConfig.generateToken(user, roles, sub.getPrivateKey());
        log.info("Token returning: {}", token);
        return ApiResponseV1.ok(LoginResponse.builder().token(token).build());
    }

    public void authWithGoogle(GoogleRequest googleRequest) {
    }

    public void authWithApple(AppleRequest appleRequest) {
    }

    public void authWithX(XRequest xRequest) {
    }

    public void authWithGithub(GithubRequest githubRequest) {
    }
}
