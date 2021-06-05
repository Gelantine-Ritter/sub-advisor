package com.subadvisor.api.event;

import com.subadvisor.DataAccess;
import com.subadvisor.api.event.dto.EventCreateDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.time.LocalDateTime;
import java.util.Set;

import static java.lang.String.format;

@Data
@AllArgsConstructor
public class EventMapper {

    private DataAccess DATA;

    public Event mapToEventEntity(EventCreateDto dto) {

        return Event.builder()
                .venue(
                        DATA.venues().findById(Long.parseLong(dto.venueId()))
                                .orElseThrow(() -> new UsernameNotFoundException(
                                        format("Venue with it - %s, not found", dto.venueId())
                                ))
                )
                .title(dto.title())
                .info(dto.info())
                .pic(dto.pic())
                .artists(Set.of(dto.artists()))
                .price(
                        Double.parseDouble(dto.price())
                )
                .eventStart(LocalDateTime.parse(dto.eventStart()))
                .eventEnd(LocalDateTime.parse(dto.eventEnd()))
                .build();
    }
}
