package refactored.chunim.config;


import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.cors.CorsConfiguration;
import refactored.chunim.endpoint.service.GaradeUserDetailService;
import refactored.chunim.security.filter.JWTAuthenticationFilter;
import refactored.chunim.security.filter.JWTAuthorizationFilter;


@Log4j2
@Service
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private final GaradeUserDetailService garadeUserDetailService;


    @Autowired
    public SecurityConfig(GaradeUserDetailService garadeUserDetailService) {
        this.garadeUserDetailService = garadeUserDetailService;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
                .and()
                .authorizeRequests()
                .antMatchers("/cars/admin/**").hasRole("ADMIN")
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager(), garadeUserDetailService));

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(garadeUserDetailService).passwordEncoder(new BCryptPasswordEncoder());

    }
}

