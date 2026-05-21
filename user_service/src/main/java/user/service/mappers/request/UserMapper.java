package user.service.mappers.request;

import user.service.dtos.requestest.user.UserCreateRequest;
import user.service.entities.UserEntity;

public class UserMapper {

    public static UserCreateRequest mapTo(UserEntity user) {
        return UserCreateRequest.builder()
                .email(user.getEmail())
                .build();
    }

    public static UserEntity mapTo(UserCreateRequest user) {
        return UserEntity.builder()
                .email(user.getEmail())
                .build();
    }
}
