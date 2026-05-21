package auth.service.configs.jwt;

import auth.service.domains.credential.entity.Credential;
import auth.service.domains.role.entity.Role;
import auth.service.domains.user.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.*;

@Service
@RequiredArgsConstructor
public class JwtConfig {
    private static final long EXPIRED = 1000 * 300 * 60 * 60;

    /// 5 phut

    public String generateToken(User user, List<Role> roles, String privateKey) {

        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + EXPIRED);

        List<String> userRoles = roles.stream().map(role -> role.getRole().getName()).toList();

        Map<String, Object> claims = new HashMap<>();
        // custom claims
        claims.put("userId", user.getId());
        claims.put("email", user.getEmail());
        claims.put("firstName", user.getFirstName());
        claims.put("lastName", user.getLastName());
        claims.put("roles", userRoles);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getEmail())
                .setIssuedAt(currentDate)
                .setExpiration(expireDate)
                .signWith(parsePrivateKey(privateKey), SignatureAlgorithm.RS256).compact();
    }

    private PrivateKey parsePrivateKey(String privateKeyPem) {

        try {

            //? Lam sach chuoi privateKey
            privateKeyPem = privateKeyPem
                    .replace("-----BEGIN PRIVATE KEY-----", "")
                    .replace("-----END PRIVATE KEY-----", "")
                    .replaceAll("\\s", "");

            byte[] decoded = Base64.getDecoder().decode(privateKeyPem);

            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decoded);

            KeyFactory keyFactory = KeyFactory.getInstance("RSA");

            return keyFactory.generatePrivate(spec);

        } catch (Exception ex) {

            throw new RuntimeException("Cannot parse private key", ex);
        }
    }

    public Claims extractAllClaims(String token, String privateKey) {

        return Jwts.parserBuilder().setSigningKey(parsePrivateKey(privateKey)).build().parseClaimsJws(token).getBody();
    }

    public String getEmail(String token, String privateKey) {
        return extractAllClaims(token, privateKey).getSubject();
    }

    public String getRole(String token, String privateKey) {
        return extractAllClaims(token, privateKey).get("role", String.class);
    }

    public Long getUserId(String token, String privateKey) {
        return extractAllClaims(token, privateKey).get("userId", Long.class);
    }

    public boolean validateToken(String token, String privateKey) {

        try {

            Jwts.parserBuilder().setSigningKey(privateKey).build().parseClaimsJws(token);

            return true;
        } catch (Exception ex) {
            System.out.println("JWT ERROR: " + ex.getMessage());
            return false;
        }
    }
}