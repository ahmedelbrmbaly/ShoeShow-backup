package iti.jets.services;

import iti.jets.model.AdminPrincipal;
import iti.jets.model.UserPrincipal;
import iti.jets.model.entities.Admin;
import iti.jets.model.entities.User;
import iti.jets.repositories.AdminRepo;
import iti.jets.repositories.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepo userRepo;
    private final AdminRepo adminRepo;

    public CustomUserDetailsService(UserRepo userRepo, AdminRepo adminRepo){
        this.userRepo = userRepo;
        this.adminRepo = adminRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(email);
        if(user != null){
            return new UserPrincipal(user);
        }
        Admin admin = adminRepo.findByEmail(email);
        if(admin != null){
            return new AdminPrincipal(admin);
        }
        throw new UsernameNotFoundException("user not found for email: " + email);
    }
}
