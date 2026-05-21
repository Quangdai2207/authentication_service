package user.service.configs.seeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import user.service.entities.Role;
import user.service.enums.RoleBaseAuthorized;
import user.service.repositories.v1.RoleRepository_V1;

@Component
public class RoleSeeder implements CommandLineRunner {
    @Autowired
    private RoleRepository_V1 roleRepository_V1;

    @Override
    public void run(String... args) throws Exception {
        for (RoleBaseAuthorized roleEnum : RoleBaseAuthorized.values()) {

            boolean exists = roleRepository_V1.existsByName(roleEnum);

            if (!exists) {
                Role role = Role.builder()
                        .name(roleEnum)
                        .description(roleEnum.getDescription())
                        .build();

                roleRepository_V1.save(role);
            }
        }
    }
}
