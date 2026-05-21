package auth.service.domains.credential_role.entity;

import auth.service.domains.credential.entity.Credential;
import auth.service.domains.role.entity.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(
        name = "credentialRoles",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {
                                "credential_id",
                                "role_id"
                        }
                )
        }
)
public class CredentialRole {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(unique = true, nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "role_id",  nullable = false)
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "credential_id",  nullable = false)
    private Credential credential;
}
