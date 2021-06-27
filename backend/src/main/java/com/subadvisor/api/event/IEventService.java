package com.subadvisor.api.event;

import com.subadvisor.api.event.dto.EventCreateDto;
import com.subadvisor.api.event.dto.EventDto;
import com.subadvisor.api.event.dto.EventUpdateDto;
import com.subadvisor.api.venue.Venue;
import com.subadvisor.api.venue.dto.IVenueDto;

import java.util.List;

public interface IEventService {
    List<EventDto> getAllEvents();
    List<EventDto> getEventsByVenue(String name);
    List<EventDto> getEventsByDate(String date);
    EventDto createEvent(EventCreateDto event);
    EventDto getEventById(Long eventId);
    EventDto updateEventById(EventUpdateDto newEvent, Long eventId);
    void deleteEventById(Long eventId);
}
