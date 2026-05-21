package auth.service.domains.credential.entity;

import auth.service.domains.credential_role.entity.CredentialRole;

import auth.service.domains.user.entity.User;
import auth.service.enums.AuthProvider;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(
        name = "credentials",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"provider", "provider_user_id"}
                )
        }
)
public class Credential {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(unique = true, nullable = false)
    private UUID id;

    /**
     * Authentication provider:
     * LOCAL, GOOGLE, GITHUB, APPLE...
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AuthProvider provider;

    /**
     * providerUserId la gia tri dinh danh do nha cung cap dich vu tra ve.
     * Khi nguoi dung duoc cac nha cung cap dich vu xac thuc danh tinh thanh cong, 1 token_id duoc tra ve va token_id
     * duoc decode de lay toan bo thong tin tu cac nha cung cap. Trong do co chua noi dung cua providerrUserId bang nhieu
     * ten khac nhau
     * Vi du:
     * GOOGLE -> sub
     * GITHUB -> id
     * FACEBOOK -> id
     * This value is stable for the same user
     * under the same OAuth client/provider.
     * Khi co duoc providerUserId nen lu vao trong DB
     */
    @Column(name = "provider_user_id", nullable = false)
    private String providerUserId;

    /**
     * BCrypt/Argon2 hash.
     * <p>
     * Nullable for OAuth accounts.
     */
    @Column(name = "password_hash", nullable = true)
    private String passwordHash;

    /**
     * Tai khoan bi Disable/ Enable
     * Vi du:
     * - Email chua xac thuc
     * - account bi cam (banned account)
     */
    @Column(nullable = false)
    private boolean enabled = false;

    /// Tai khoan tam khoa sau nhieu lan Dang Nhap that bai
    @Column(nullable = false)
    private boolean accountNonLocked = true;

    /**
     * Tinh nang doanh nghiep: Chinh sach thoi han mat khau (mat khau duocc yeu cau thay doi dinh ky)
     */
    @Column(nullable = false)
    private boolean credentialsNonExpired = true;

    /// So lan dang nhap that bai nhieu lan
    @Column(nullable = false)
    private int failedAttempts = 0;

    /// Thoi han khoa cho den khi
    @Column(nullable = true)
    private Instant lockedUntil;

    /// Lan cuoi thay doi password khi nao
    @Column(nullable = true)
    private Instant passwordChangedAt;

    /// Dang nhao thanh cong lan cuoi gan nhat
    @Column(nullable = true)
    private Instant lastLoginAt;

    @Version
    private Long version;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false, updatable = true)
    private Instant updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "credential", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    Set<CredentialRole> credentialRoles = new HashSet<>();
}
