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

import static java.lang.String.format;


@Service
public class EventService extends DataAccess implements IEventService {

    @Autowired
    DataAccess DATA;

    @Autowired
    CustomMapper mapper;

    @Override
    public List<Event> getAllEvents() {
        return DATA.events().findAll();
    }

    @Override
    public List<Event> getEventsByVenue(String venueId) {
        return DATA.events()
                .findByVenueId(Long.parseLong(venueId))
                .filter(opt -> !opt.isEmpty())
                .orElseThrow(
                        () -> new EntityNotFoundException(
                                format("No Events found for %s ", venueId)
                        )
                );
    }

    @Override
    public Event createEvent(EventCreateDto dto) {

        return DATA.events().save(
                new EventMapper(DATA).mapToEventEntity(dto)
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
    public Event updateEventById(EventUpdateDto updateEvent, Long eventId) {
        return DATA.events()
                .findById(eventId)
                .map(
                        event -> new EventMapper(DATA).updatingEventEntity(event, updateEvent)
                )
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
