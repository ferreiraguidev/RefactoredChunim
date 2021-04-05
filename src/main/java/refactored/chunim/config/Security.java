package refactored.chunim.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import refactored.chunim.endpoint.service.GaradeUserDetailService;

@EnableWebSecurity
@Log4j2
@RequiredArgsConstructor

public class Security extends WebSecurityConfigurerAdapter {

    private final GaradeUserDetailService garadeUserDetailService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/cars/admin/**").hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .and()
                .httpBasic();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder delegatingPasswordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        log.info("Password encoded{}", delegatingPasswordEncoder.encode("testing"));

        auth.inMemoryAuthentication()
                .withUser("Guilherme")
                .password(delegatingPasswordEncoder.encode("crewlife2020"))
                .roles("ADMIN");

        auth.userDetailsService(garadeUserDetailService).passwordEncoder(delegatingPasswordEncoder);
    }
}
