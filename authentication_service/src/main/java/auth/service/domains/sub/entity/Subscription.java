package auth.service.domains.sub.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "subscriptions")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(unique = true, nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String appName;

    @Column(nullable = false)
    private String origin;

    @Column(nullable = false)
    private String clientId;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String privateKey;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String publicKey;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Instant createdAt;

    @Column(nullable = false, updatable = true)
    @UpdateTimestamp
    private Instant updatedAt;

    @Column(nullable = false)
    private boolean isDeleted = false;

}
