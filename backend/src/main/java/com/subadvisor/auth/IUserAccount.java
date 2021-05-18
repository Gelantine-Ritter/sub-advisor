package com.subadvisor.auth;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface IUserAccount {
    Optional<UserDetails> findByUsername(String username);
    Optional<UserDetails> createAccount(RegistrationRequest registrationRequest);
}
