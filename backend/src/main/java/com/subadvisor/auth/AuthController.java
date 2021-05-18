package com.subadvisor.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {


    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private RegistrationService registrationService;


    // Endpoint to test authentification
    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    @PostMapping("/authenticate/")
    public ResponseEntity<?> createAuthenticationToken
            (@RequestBody AuthenticationRequest authenticationRequest) {

        return new ResponseEntity<>(
                authenticationService.authenticateUser(authenticationRequest),
                HttpStatus.OK
        );
    }

    @PostMapping("/authenticate/registrate/")
    public ResponseEntity<?> registerAccount (@RequestBody RegistrationRequest registrationRequest) {
        return new ResponseEntity<>(
                registrationService.registrateUser(registrationRequest),
                HttpStatus.OK
        );
    }
}
