package com.subadvisor.api.event.dto;

import com.subadvisor.api.venue.Venue;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventDto {

    private Long id;
    private String venueId;
    private String title;
    private String info;
    private Set<String> artists;
    private double price;
    private byte[] pic;
    private LocalDateTime created;
    private LocalDateTime modifiedAt;
    private LocalDateTime eventStart;
    private LocalDateTime eventEnd;

}
