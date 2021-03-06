package com.subadvisor.api.event.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.Accessors;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(fluent = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class EventFalseDto {

    private String id;
    private String venueId;
    private String title;
    private String info;
    private String[] artists;
    private String price;
    private String eventStart;
    private String eventEnd;
    private byte[] pic;
}
