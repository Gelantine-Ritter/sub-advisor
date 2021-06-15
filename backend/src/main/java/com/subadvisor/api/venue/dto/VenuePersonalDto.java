package com.subadvisor.api.venue.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.subadvisor.api.auth.dto.RegistrationResponseDto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class VenuePersonalDto implements IVenueDto, RegistrationResponseDto {

    private String id;
    private String username;
    private String name;
    private String email;
    private String info;
    private String mobile;
    private String website;
    private Map<String, String> hours;
    private Map<String, String> address;
    private byte[] pic;
    private String ROLE;


    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime created;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime modifiedAt;
}
