package auth.service.domains.auth.dtos.response.v1;

import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private String token;
}
