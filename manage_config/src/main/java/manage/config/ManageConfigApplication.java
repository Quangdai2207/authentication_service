package manage.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ManageConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManageConfigApplication.class, args);
    }

}
