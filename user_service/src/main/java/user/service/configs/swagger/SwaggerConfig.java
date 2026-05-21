package user.service.configs.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

import java.util.Map;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                /// Config Project document Apis
                .info(new Info()
                        .title("Ecommerce Mini - API Document")
                        .description("Ecommerce Mini with OAuth authentication")
                        .version("V1.0")
                        .contact(new Contact()
                                .extensions(Map.of("Supports: ", "0819099931", "Email: ", "daitran.inbox@gmail.com"))
                        )
                )
                // Add security requirement for JWT
                .addSecurityItem(new SecurityRequirement().addList("BearerAuth"))

                // Define the security scheme
                .components(new Components()
                        .addSecuritySchemes("BearerAuth",
                                new SecurityScheme()
                                        .name("Authorization")
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                        )
                );
    }
}
