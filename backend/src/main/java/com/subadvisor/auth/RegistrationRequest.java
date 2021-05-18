package com.subadvisor.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegistrationRequest {
    // SHARED PROPS
    private String username;
    private String email;
    private String password;
    private boolean isVenue;

    // VENUE ONLY
    private String title;
    private String info;

    // USER ONLY
}

