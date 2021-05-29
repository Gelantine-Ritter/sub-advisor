package com.subadvisor.api.auth;

import com.subadvisor.api.auth.dto.AuthenticationRequest;
import com.subadvisor.api.auth.dto.RegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

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
    public ResponseEntity<?> registrateUser (@RequestBody RegistrationRequest registrationRequest) {
        return new ResponseEntity<>(
                authenticationService.registrateUser(registrationRequest),
                HttpStatus.OK
        );
    }

}
