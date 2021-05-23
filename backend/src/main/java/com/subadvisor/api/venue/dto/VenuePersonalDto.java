package com.subadvisor.api.venue.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VenuePersonalDto {

    private String username;
    private String name;
    private String email;
    private String info;

    private LocalDateTime created;
    private LocalDateTime modifiedAt;

}
