package com.subadvisor.api.event;

import com.subadvisor.CustomMapper;
import com.subadvisor.DataAccess;
import com.subadvisor.api.event.dto.EventCreateDto;
import com.subadvisor.api.event.dto.EventDto;
import com.subadvisor.api.event.dto.EventUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;


@Service
public class EventService extends DataAccess implements IEventService {

    @Autowired
    DataAccess DATA;

    @Autowired
    private CustomMapper mapper;

    @Override
    public List<EventDto> getAllEvents() {
        return DATA.events()
                .findAll()
                .stream()
                .map(event -> mapper.eventToEventDto(event))
                .collect(Collectors.toList());
    }

    @Override
    public List<EventDto> getEventsByVenue(String venueId) {
        return DATA.events()
                .findByVenueId(Long.parseLong(venueId))
                .stream()
                .map(event -> mapper.eventToEventDto(event))
                .collect(Collectors.toList());
    }

    @Override
    public Event createEvent(EventCreateDto dto) {

        return DATA.events()
                .save(new EventMapper(DATA).mapToEventEntity(dto)
        );
    }

    @Override
    public EventDto getEventById(Long eventId) {
        return DATA.events()
                .findById(eventId)
                .map(event -> mapper.eventToEventDto(event))
                .orElseThrow(
                        () -> new EntityNotFoundException(
                                format("Event with id - %s, not found", eventId)
                        )
                );
    }

    @Override
    public EventDto updateEventById(EventUpdateDto eventUpdateDto, Long eventId) {
        return DATA.events()
                .findById(eventId)
                .map(event -> {
                    mapper.eventUpdateDtoToEvent(eventUpdateDto, event);
                    return event;
                })
                .map(event -> DATA.events().save(event))
                .map(event ->  mapper.eventToEventDto(event))
                .orElseThrow(
                        () -> new EntityNotFoundException(
                                format("Event with id - %s, not found", eventId)
                        )
                );
    }

    @Override
    public void deleteEventById(Long eventId) {
        DATA.events().deleteById(eventId);
    }
}
