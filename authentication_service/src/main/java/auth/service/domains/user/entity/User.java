package auth.service.domains.user.entity;

import auth.service.domains.credential.entity.Credential;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(unique = true, nullable = false)
    private UUID id;

    @Column(nullable = true)
    private String firstName;

    @Column(nullable = true)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = true,  unique = true)
    private String phone;

    @Column(nullable = true)
    private boolean gender;

    @Column(nullable = true)
    private LocalDate dob;

    @Column(nullable = true)
    private String avatar;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(nullable = true, updatable = true)
    private Instant updateAt;

    @Column(comment = "False: undeleted, True: disable ")
    private boolean isDeleted = false;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Credential> credentials = new HashSet<>();
}
