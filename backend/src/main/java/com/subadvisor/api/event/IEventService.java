package com.subadvisor.api.event;

import com.subadvisor.api.event.dto.EventCreateDto;

import java.util.List;

public interface IEventService {
    List<Event> getAllEvents();
    List<Event> getEventsByVenue(String name);
    Event createEvent(EventCreateDto event);
    Event getEventById(Long eventId);
    void deleteEventById(Long eventId);
}
