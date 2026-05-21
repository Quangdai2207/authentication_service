package auth.service.domains.origin.entity;

import auth.service.domains.sub.entity.Subscription;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "originUrls")
public class OriginUrl {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, updatable = false)
    private UUID id;

    // Ap dung tra response cho FE server
    private String originFE;

    // Ap dung dieu huong cho Service MVC
    private String originRedirect;

    // Dung de xac thuc origin da dang ky chua
    @Column(nullable = false)
    private String clientId;

    @Column(nullable = false)
    private String clientSecret;

    // Mot Subscription co nhieu origin dang ky
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "subscription_id", nullable = false)
    private Subscription sub;
}
