package auth.service.domains.role.dtos.response;

import auth.service.domains.role.enums.RoleAuthorization;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RoleResponse {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String role;
}
