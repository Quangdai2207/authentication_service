package user.service.dtos.requestest.auth.oauth2;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class XRequest {
    @JsonProperty("token_id")
    private String token;
}
