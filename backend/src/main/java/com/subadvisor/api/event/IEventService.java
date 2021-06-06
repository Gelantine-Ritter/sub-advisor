package com.subadvisor.api.event;

import com.subadvisor.api.event.dto.EventCreateDto;
import com.subadvisor.api.event.dto.EventUpdateDto;
import com.subadvisor.api.venue.Venue;
import com.subadvisor.api.venue.dto.IVenueDto;

import java.util.List;

public interface IEventService {
    List<Event> getAllEvents();
    List<Event> getEventsByVenue(String name);
    Event createEvent(EventCreateDto event);
    Event getEventById(Long eventId);
    Event updateEventById(EventUpdateDto newEvent, Long eventId);
    void deleteEventById(Long eventId);
}
