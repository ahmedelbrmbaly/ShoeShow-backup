package iti.jets.controllers;

import iti.jets.model.dtos.LoginRequest;
import iti.jets.model.dtos.RegisterRequest;
import iti.jets.model.dtos.Token;
import iti.jets.model.entities.User;
import iti.jets.services.AuthService;
import iti.jets.services.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@Slf4j
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = "/login", produces = "application/json")
    public ResponseEntity<Token> login(@RequestBody(required = false)LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.validateUser(loginRequest)) ;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterRequest registerRequest) {
        log.info(registerRequest.toString());
        authService.saveUser(registerRequest);
        return ResponseEntity.ok().build();
    }

}
