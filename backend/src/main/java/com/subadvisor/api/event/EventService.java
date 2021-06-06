package com.subadvisor.api.event;

import com.subadvisor.DataAccess;
import com.subadvisor.api.event.dto.EventCreateDto;
import com.subadvisor.api.venue.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static java.lang.String.format;


@Service
public class EventService extends DataAccess implements IEventService {

    @Autowired
    DataAccess DATA;

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
    public Event getEventById(Long eventId) {
        return DATA.events()
                .findById(eventId)
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
