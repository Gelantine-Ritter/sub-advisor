package com.subadvisor.api.event.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.Accessors;
import javax.validation.constraints.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventCreateDto {

    @NotNull(message = "Please provide a venueId")
    @NotEmpty(message = "venueId should not be empty")
    private String venueId;
    @NotNull(message = "Please provide a title")
    @NotEmpty(message = "title should not be empty")
    private String title;
    private String info;
    private String[] artists;
    private String price;
    private String eventStart;
    private String eventEnd;
    private String pic;
    private String[] tags;
}
