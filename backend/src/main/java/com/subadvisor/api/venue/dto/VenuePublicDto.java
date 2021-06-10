package com.subadvisor.api.venue.dto;

import com.subadvisor.api.event.Event;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VenuePublicDto implements IVenueDto {

    private String id;
    private String name;
    private String email;
    private String info;
    private Map<String, String> hours;
    private String website;
    private Map<String, String> address;
    private byte[] pic;

}
