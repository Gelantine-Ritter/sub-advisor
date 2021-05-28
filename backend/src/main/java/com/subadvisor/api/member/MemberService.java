package com.subadvisor.api.member;

import com.subadvisor.config.WebConfig;
import com.subadvisor.registration.*;
import com.subadvisor.registration.dto.RegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

/**
 * Spring-Service responsible to retrieve Users
 */

@Service
public class MemberService implements UserDetailsService, IMemberService {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return memberRepository
                .findByUsername(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                format("User with username - %s, not found", username)
                        )
                );
    }

    public void signUpUser(RegistrationRequest registrationRequest) {

        User user;

        if(!userRepository.findByUsername(registrationRequest.getUsername()).isPresent()){

            if (registrationRequest.getUserRole().equals(String.valueOf(UserRole.MEMBER))){
                user = User.builder()
                        .username(registrationRequest.getUsername())
                        .email(registrationRequest.getEmail())
                        .password(new WebConfig().bCryptPasswordEncoder().encode(registrationRequest.getPassword()))
                        .userRole(UserRole.MEMBER)
                        .enabled(true)
                        .build();
            }else {
                user = User.builder()
                        .username(registrationRequest.getUsername())
                        .email(registrationRequest.getEmail())
                        .password(new WebConfig().bCryptPasswordEncoder().encode(registrationRequest.getPassword()))
                        .userRole(UserRole.VENUE)
                        .enabled(true)
                        .build();
            }
            userRepository.save(user);

            System.out.println("Saved User");
        }else {
            System.out.println("Username already in Use");
        }
    }

    public void confirmUser(ConfirmationToken confirmationToken) {

        final User user = confirmationToken.getUser();

        user.enabled(true);

        userRepository.save(user);

        confirmationTokenService.deleteConfirmationToken(confirmationToken.getId());

    }
}


