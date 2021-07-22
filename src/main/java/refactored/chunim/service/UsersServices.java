package refactored.chunim.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import refactored.chunim.model.Users;
import refactored.chunim.repository.UsersRepository;

@Slf4j
@Service
public class UsersServices {

    private final UsersRepository usersRepository;

    public UsersServices(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public Users saveUser(Users user) {
        return usersRepository.save(user);
    }

    public Users findByUser(Integer id) {
        return usersRepository.findById((long) id)
                .orElseThrow(() -> new NullPointerException("User not found!"));
    }

    public void createUserAdmin() {
        try {
            if (usersRepository.findByUsername("admin").isEmpty()) {
                Users u = new Users();
                u.setName("Administrator");
                u.setUsername("admin");
                u.setPassword(new BCryptPasswordEncoder().encode("pass123"));
                u.setAdmin(true);

                usersRepository.save(u);
            }
        } catch (Exception e) {
            log.debug(e.toString());
        }
    }

    public void createUser() {
        try {
            if (usersRepository.findByUsername("user").isEmpty()) {
                Users u = new Users();
                u.setName("CommomUser");
                u.setUsername("user");
                u.setPassword(new BCryptPasswordEncoder().encode("pass123"));
                u.setAdmin(false);

                usersRepository.save(u);
            }
        } catch (Exception e) {
            log.debug(e.toString());
        }
    }


}
