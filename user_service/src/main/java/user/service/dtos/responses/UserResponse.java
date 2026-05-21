package user.service.dtos.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    /// Neu co bat ky truong nao null thi java auto skip binding truong null Json
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
