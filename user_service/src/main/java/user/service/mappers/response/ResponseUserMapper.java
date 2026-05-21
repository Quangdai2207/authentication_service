package user.service.mappers.response;

import lombok.*;
import user.service.dtos.responses.UserResponse;
import user.service.entities.UserEntity;

public class ResponseUserMapper {

    public static UserResponse mapTo(UserEntity user) {
            return UserResponse.builder()
                    .email(user.getEmail())
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .gender(user.getAvatar())
                    .build();
    }
}
