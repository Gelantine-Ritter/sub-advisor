package com.subadvisor.api.auth;

import com.subadvisor.api.auth.dto.AuthenticationRequest;
import com.subadvisor.api.auth.dto.AuthenticationResponse;
import com.subadvisor.api.user.UserRepository;
import com.subadvisor.api.venue.VenueRepository;
import com.subadvisor.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class AuthenticationService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    VenueRepository venueRepository;

    @Autowired
    private JwtUtil jwtTokenUtil;


    public AuthenticationResponse authenticateUser(AuthenticationRequest authReq) {
        // TODO: It should be Best Practice to use User- and VenueService insteat of repository/DAO diretly
        return authenticateByRepo(venueRepository, authReq)
                .or(
                        () -> authenticateByRepo(userRepository, authReq)
                )
                .orElseThrow(() -> new EntityNotFoundException("Account not found"));
    }

    private Optional<AuthenticationResponse> authenticateByRepo(IUserAccount repo, AuthenticationRequest authReq) {
        return repo
                .findByUsername(authReq.getUsername())
                .filter(account -> passwordEncoder.matches(authReq.getPassword(), account.getPassword()))
                .map(account -> new AuthenticationResponse(jwtTokenUtil.generateToken(authReq.getUsername())));
    }
}
