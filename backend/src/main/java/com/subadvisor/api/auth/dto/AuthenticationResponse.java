package com.subadvisor.api.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class AuthenticationResponse {

    @Getter
    private final String jwt;

    @Getter
    private final String userId;

}
