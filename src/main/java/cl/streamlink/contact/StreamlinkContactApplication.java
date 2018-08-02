package cl.streamlink.contact;

import cl.streamlink.contact.domain.Role;
import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.service.UserService;
import cl.streamlink.contact.web.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.ArrayList;
import java.util.Collections;

@SpringBootApplication
@ComponentScan(basePackages = "cl.streamlink.contact")
public class StreamlinkContactApplication implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(StreamlinkContactApplication.class);

    @Autowired
    UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(StreamlinkContactApplication.class, args);
    }

    @Override
    public void run(String... params) {

        try {
            UserDTO admin = new UserDTO();
            admin.setLastname("admin");
            admin.setFirstname("admin");

            admin.setPassword("admin");
            admin.setEmail("admin@email.com");
            admin.setRoles(new ArrayList<>(Collections.singletonList(Role.ROLE_ADMIN)));

            userService.signup(admin);
        } catch (ContactApiException e) {
            logger.warn(e.getMessage());
        }
        try {
            UserDTO client = new UserDTO();
            client.setLastname("client");
            client.setFirstname("client");
            client.setPassword("client");
            client.setEmail("client@email.com");
            client.setRoles(new ArrayList<>(Collections.singletonList(Role.ROLE_CLIENT)));

            userService.signup(client);
        } catch (ContactApiException e) {
            logger.warn(e.getMessage());
        }
    }
}
