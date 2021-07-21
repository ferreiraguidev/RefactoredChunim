package refactored.chunim.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import refactored.chunim.repository.AdminRepository;
import refactored.chunim.model.ApplicationUser;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class GaradeUserDetailService implements UserDetailsService {

    private final AdminRepository adminRepository;


    @Autowired
    public GaradeUserDetailService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser applicationUser = loadApplicationUserByUsername(username);
        return new CustomUserDetails(applicationUser);
    }

    public ApplicationUser loadApplicationUserByUsername(String username) {
        return Optional.ofNullable(adminRepository.findByUsername(username))
                .orElseThrow(() -> new UsernameNotFoundException("ApplicationUser not found"));
    }

    private final static class CustomUserDetails extends ApplicationUser implements UserDetails {

        private CustomUserDetails(ApplicationUser applicationUser) {
            super(applicationUser);
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            List<GrantedAuthority> authorityListProfessor = AuthorityUtils.createAuthorityList("ROLE_ADMIN");
            List<GrantedAuthority> authorityListStudent = AuthorityUtils.createAuthorityList("ROLE_USER");
            return this.getGarageAdmin() != null ? authorityListProfessor : authorityListStudent;

        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }
}
