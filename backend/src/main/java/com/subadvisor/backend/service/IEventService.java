package com.subadvisor.backend.service;

import com.subadvisor.backend.model.Event;
import java.util.List;
import java.util.Optional;

public interface IEventService {
    List<Event> getAllEvents();
    void createEvent(Event event);
    Optional<Event> getEventById(Long eventId);
    void deleteEventById(Long eventId);
}
