package refactored.chunim.security.filter;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import refactored.chunim.model.Users;
import refactored.chunim.repository.UsersRepository;

import java.util.List;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    private final UsersRepository usersRepository;

    public CustomUserDetailsService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Users users = usersRepository.findByUsername(login).get();

        List<GrantedAuthority> authorityListAdmin = AuthorityUtils.createAuthorityList("ROLE_USER", "ROLE_ADMIM");
        List<GrantedAuthority> role_user = AuthorityUtils.createAuthorityList("ROLE_USER");

        if(users.isAdmin()) {
            return new User(users.getUsername(), users.getPassword(), authorityListAdmin);
        } else {
            return new User(users.getUsername(), users.getPassword(), role_user);
        }
    }
}
