package refactored.chunim.security.filter;


import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import refactored.chunim.endpoint.service.GaradeUserDetailService;
import refactored.chunim.model.ApplicationUser;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static refactored.chunim.security.filter.Constants.*;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager, GaradeUserDetailService garadeUserDetailService) {
        super(authenticationManager);
        this.garadeUserDetailService = garadeUserDetailService;

    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(HEADER);
        if (header == null || header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }
        UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationToken(request);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);

    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(HttpServletRequest req) {
        String token = req.getHeader(HEADER);
        if (token == null) return null;
        String username = Jwts.parser().setSigningKey(SECRET)
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody()
                .getSubject();
        UserDetails userDetails = garadeUserDetailService.loadUserByUsername(username);
        ApplicationUser applicationUser = garadeUserDetailService.loadApplicationUserByUsername(username);

        return username != null ? new UsernamePasswordAuthenticationToken(applicationUser, null, userDetails.getAuthorities()) : null;


    }

    private final GaradeUserDetailService garadeUserDetailService;


}


