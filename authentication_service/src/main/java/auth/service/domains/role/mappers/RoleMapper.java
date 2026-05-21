package auth.service.domains.role.mappers;

import auth.service.domains.role.dtos.response.RoleResponse;
import auth.service.domains.role.entity.Role;

public class RoleMapper {

    public static RoleResponse mapTo(Role role) {
        return RoleResponse.builder()
                .id(role.getId())
                .role(role.getRole().name())
                .build();
    }
}
