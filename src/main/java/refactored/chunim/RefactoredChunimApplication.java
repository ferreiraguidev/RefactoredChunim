package refactored.chunim;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import refactored.chunim.service.UsersServices;

@SpringBootApplication
public class RefactoredChunimApplication {

    @Autowired
    UsersServices usersServices;

    public static void main(String[] args) {
        SpringApplication.run(RefactoredChunimApplication.class, args);
    }

    @Bean
    InitializingBean sendDataUser() {
        return () -> {
            usersServices.createUserAdmin();
            usersServices.createUser();
        };
    }
}
