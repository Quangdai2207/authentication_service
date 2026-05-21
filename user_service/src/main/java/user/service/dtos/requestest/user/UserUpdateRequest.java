package user.service.dtos.requestest.user;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserUpdateRequest {
    private String gender;
    private LocalDate dob;
}
