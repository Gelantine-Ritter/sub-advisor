package com.subadvisor.api.auth;

import com.subadvisor.api.auth.dto.*;
import com.subadvisor.api.member.Member;
import com.subadvisor.api.member.MemberRepository;
import com.subadvisor.api.venue.Venue;
import com.subadvisor.api.venue.VenueRepository;
import com.subadvisor.api.venue.VenueService;
import com.subadvisor.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    public RegistrationResponseDto registrateUser(IRegistrationRequestDto registrationRequestDto) {
        if (registrationRequestDto instanceof Member){
            // Create Member
            System.out.println("member");
            return null;
        }else if (registrationRequestDto instanceof Venue){
                System.out.println("venue");
                return venueService.createVenue((Venue) registrationRequestDto);
        }
        System.out.println("null");
        return null;
    }
}
