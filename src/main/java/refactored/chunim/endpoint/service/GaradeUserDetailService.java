package refactored.chunim.endpoint.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import refactored.chunim.endpoint.repository.AdminRepository;

import java.util.Optional;


@Service
@RequiredArgsConstructor

public class GaradeUserDetailService implements UserDetailsService {

    private final AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return Optional.ofNullable(adminRepository.findByUsername(username))
                .orElseThrow(()-> new UsernameNotFoundException("Admin Not found"));
    }
}
