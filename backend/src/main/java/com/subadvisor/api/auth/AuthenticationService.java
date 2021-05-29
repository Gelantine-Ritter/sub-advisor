package com.subadvisor.api.auth;

import com.subadvisor.api.auth.dto.AuthenticationRequest;
import com.subadvisor.api.auth.dto.AuthenticationResponse;
import com.subadvisor.api.auth.dto.RegistrationResponse;
import com.subadvisor.api.event.EventService;
import com.subadvisor.api.member.MemberRepository;
import com.subadvisor.api.venue.Venue;
import com.subadvisor.api.venue.VenueRepository;
import com.subadvisor.api.venue.VenueService;
import com.subadvisor.config.WebConfig;
import com.subadvisor.api.auth.dto.RegistrationRequest;
import com.subadvisor.security.JwtUtil;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class AuthenticationService {

    @Autowired
    VenueService venueService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    VenueRepository venueRepository;

    @Autowired
    private JwtUtil jwtTokenUtil;


    public AuthenticationResponse authenticateUser(AuthenticationRequest authReq) {
        // TODO: It should be Best Practice to use User- and VenueService insteat of repository/DAO diretly
        return authenticateByRepo(venueRepository, authReq)
                .or(
                        () -> authenticateByRepo(memberRepository, authReq)
                )
                .orElseThrow(() -> new EntityNotFoundException("Account not found"));
    }

    private Optional<AuthenticationResponse> authenticateByRepo(IUserAccount repo, AuthenticationRequest authReq) {
        return repo
                .findByUsername(authReq.getUsername())
                .filter(account -> passwordEncoder.matches(authReq.getPassword(), account.getPassword()))
                .map(account -> new AuthenticationResponse(jwtTokenUtil.generateToken(authReq.getUsername())));
    }

    public Optional<RegistrationResponse> registrateUser(RegistrationRequest registrationRequest) {
        if (venueRepository.findByUsername(registrationRequest.getUsername()).isEmpty()) {

            Venue venue = Venue.builder()
                    .username(registrationRequest.getUsername())
                    .name(registrationRequest.getName())
                    .email(registrationRequest.getEmail())
                    .password(registrationRequest.getPassword())
                    .enabled(true)
                    .build();
            venueService.createVenue(venue);
            System.out.println("Created Venue: " + venue.name());

        }else {
            System.out.println("Username already in Use");
        }
        return null;
    }
}
