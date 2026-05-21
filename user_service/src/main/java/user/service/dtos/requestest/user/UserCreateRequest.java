package user.service.dtos.requestest.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import user.service.dtos.responses.CredentialResponse;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCreateRequest {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String firstName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String lastName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String email;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String phone;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String gender;

    @Builder.Default
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<CredentialResponse> credentials = new HashSet<>();
}
