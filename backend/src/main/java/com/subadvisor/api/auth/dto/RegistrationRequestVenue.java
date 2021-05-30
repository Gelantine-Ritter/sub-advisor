package com.subadvisor.api.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegistrationRequestVenue implements IRegistrationRequestDto{
    private String username;
    private String name;
    private String email;
    private String password;
}
