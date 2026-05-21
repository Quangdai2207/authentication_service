package auth.service.domains.role.entity;


import auth.service.domains.credential_role.entity.CredentialRole;
import auth.service.domains.role.enums.RoleAuthorization;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private RoleAuthorization role;

    @Column(nullable = true)
    private String description;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<CredentialRole> credentialRoles = new HashSet<>();
}
