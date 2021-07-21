package refactored.chunim.config;


import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import refactored.chunim.repository.UsersRepository;
import refactored.chunim.security.filter.CustomUserDetailsService;
import refactored.chunim.security.filter.JWTAuthenticationFilter;
import refactored.chunim.security.filter.JWTAuthorizationFilter;


@Log4j2
@Service
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String ADMIN = "ADMIN";
    private static final String USER = "USER";

    private final UsersRepository usersRepository;
    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(UsersRepository usersRepository, CustomUserDetailsService userDetailsService) {
        this.usersRepository = usersRepository;
        this.userDetailsService = userDetailsService;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests().antMatchers(HttpMethod.GET, "/login").permitAll()

                .antMatchers("/**/admin/**").hasRole(ADMIN)
                .antMatchers("/**/user/**").hasRole(USER)
                .antMatchers("/**/cars/").hasRole(ADMIN)
                .anyRequest().authenticated()
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager(), usersRepository))
                .addFilter(new JWTAuthorizationFilter(authenticationManager(), userDetailsService));
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
}

