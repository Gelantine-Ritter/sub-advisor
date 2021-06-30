package com.subadvisor.api.member.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {

    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Map<String, String> events;

    private String ROLE;

    private String pic;

}
