package com.subadvisor.api.event.dto;

import com.subadvisor.api.venue.Venue;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventDto {

    private String id;
    private String venueId;
    private String title;
    private String info;
    private String[] artists;
    private String[] tags;
    private String price;
    private String pic;
    private LocalDateTime created;
    private LocalDateTime modifiedAt;
    private LocalDateTime eventStart;
    private LocalDateTime eventEnd;
    private LocalDate date;
}
