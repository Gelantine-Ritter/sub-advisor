package com.subadvisor.registration.dto;

import com.subadvisor.user.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegistrationRequest {
    private UserRole userRole;
    private String username;
    private String email;
    private String password;
}
