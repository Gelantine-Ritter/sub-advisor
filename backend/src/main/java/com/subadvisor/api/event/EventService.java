package com.subadvisor.api.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.subadvisor.DataAccess;
import com.subadvisor.api.event.dto.EventCreateDto;
import com.subadvisor.api.venue.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class EventService extends DataAccess implements IEventService {

    @Autowired
    EventRepository eventRepo;

    @Autowired
    VenueRepository venueRepo;

    @Autowired
    DataAccess DATA;

    @Override
    public List<Event> getAllEvents() {
        return eventRepo.findAll();
    }

    @Override
    public Event createEvent(EventCreateDto dto) {

        return DATA.eventRepository().save(
                new EventMapper(DATA).mapToEventEntity(dto)
        );
        //objectMapper.convertValue(eventCreateDto, Event.class)
    }

    @Override
    public Optional<Event> getEventById(Long eventId) {
        return eventRepo.findById(eventId);
    }

    @Override
    public void deleteEventById(Long eventId) {
        eventRepo.deleteById(eventId);
    }
}
