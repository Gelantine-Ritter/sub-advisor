package com.subadvisor.api.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class MemberUpdateDto {

    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String pic;

}
