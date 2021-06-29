package com.subadvisor.api.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberRegistrateDto {

    @NotNull
    private String username;

    @NotNull
    private String email;
    @NotNull
    private String password;

}
