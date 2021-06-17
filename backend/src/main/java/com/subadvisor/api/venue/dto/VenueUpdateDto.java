package com.subadvisor.api.venue.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VenueUpdateDto {

    private String username;
    private String password;
    private String name;
    private String email;
    private String info;
    private String mobile;
    private String website;
    private Map<String, String> hours;
    private Map<String, String> address;
    private String pic;

}
