package com.subadvisor.event;

import com.subadvisor.event.Event;
import java.util.List;
import java.util.Optional;

public interface IEventService {
    List<Event> getAllEvents();
    void createEvent(Event event);
    Optional<Event> getEventById(Long eventId);
    void deleteEventById(Long eventId);
}
