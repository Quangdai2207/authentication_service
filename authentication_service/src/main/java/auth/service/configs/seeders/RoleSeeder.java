package auth.service.configs.seeders;

import auth.service.domains.role.entity.Role;
import auth.service.domains.role.enums.RoleAuthorization;
import auth.service.domains.role.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Class Seeder du lieu cho ROLE voi kieu du lieu trong DB la ENUM
 * */
@Component
public class RoleSeeder implements CommandLineRunner {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        for (RoleAuthorization roleEnum : RoleAuthorization.values()) {

            boolean exists = roleRepository.existsByRole(roleEnum);

            if (!exists) {
                Role role = Role.builder()
                        .role(roleEnum)
                        .description(roleEnum.getDescription())
                        .build();

                roleRepository.save(role);
            }
        }
    }
}
