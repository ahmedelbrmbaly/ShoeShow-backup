package iti.jets.services;

import iti.jets.exceptions.BadRequestException;
import iti.jets.exceptions.ResourceNotFoundException;
import iti.jets.model.dtos.LoginRequest;
import iti.jets.model.dtos.RegisterRequest;
import iti.jets.model.dtos.Token;
import iti.jets.model.entities.User;
import iti.jets.model.entities.UserAddress;
import iti.jets.repositories.AdminRepo;
import iti.jets.repositories.UserRepo;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepo userRepo;
    private final AdminRepo adminRepo;
    private final PasswordEncoder passwordEncoder;

    public AuthService(
             AuthenticationManager authenticationManager
            , JwtService jwtService
            , UserRepo userRepo
            , AdminRepo adminRepo
            , PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userRepo = userRepo;
        this.adminRepo = adminRepo;
        this.passwordEncoder = passwordEncoder;
    }


    public Token validateUser(LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
            throw new BadRequestException("Email and Password required");
        }
        Authentication authentication;
        try{
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password));
        }catch (BadCredentialsException e){
            throw new BadCredentialsException("Invalid username or password");
        }


        if (authentication.isAuthenticated()) {
            boolean hasUserRole = authentication.getAuthorities().stream()
                    .anyMatch(auth -> auth.getAuthority().equals("ROLE_USER"));
            boolean hasAdminRole= authentication.getAuthorities().stream()
                    .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));

            if (hasUserRole) {
                String jwtToken = jwtService.generateJwtToken(email);
                Long userId = userRepo.findByEmail(email).getUserId();
                Token token = new Token();
                token.setUserId(userId);
                token.setToken(jwtToken);
                return token;
            }
            if(hasAdminRole){
                String jwtToken = jwtService.generateJwtToken(email);
                Long adminId = adminRepo.findByEmail(email).getAdminId();
                Token token = new Token();
                token.setUserId(adminId);
                token.setToken(jwtToken);
                return token;
            }

            throw new ResourceNotFoundException("Invalid email/password supplied");
        } else {
            throw new ResourceNotFoundException("Invalid email/password supplied");
        }
    }


    public void saveUser(RegisterRequest registerRequest) {
        User user = User.builder()
                .name(registerRequest.getFirstname() + " " + registerRequest.getLastname())
                .email(registerRequest.getEmail())
                .phoneNumber(registerRequest.getPhone())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .job(registerRequest.getJob())
                .interests(registerRequest.getInterest())
                .birthdate(registerRequest.getBirthdate())
                .creditLimit(registerRequest.getCreditLimit())
                .addresses(new ArrayList<>())
                .createdAt(Timestamp.from(Instant.now()))
                .build();
        UserAddress userAddress = new UserAddress();
        userAddress.setUser(user);
        userAddress.setBuildingNumber(registerRequest.getBuildingNumber());
        userAddress.setStreet(registerRequest.getStreet());
        userAddress.setState(registerRequest.getState());
        userAddress.setIsDefault(true);
        user.getAddresses().add(userAddress);
        userRepo.save(user);
    }
}
