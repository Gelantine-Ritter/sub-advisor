package com.subadvisor.api.event;

import com.subadvisor.DataAccess;
import com.subadvisor.api.event.dto.EventCreateDto;
import com.subadvisor.api.event.dto.EventDto;
import com.subadvisor.api.event.dto.EventUpdateDto;
import com.subadvisor.security.CreatorCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class EventController {

    @Autowired
    EventService eventService;

    @Autowired
    CreatorCheck creatorCheck;

    @GetMapping("/events")
    public ResponseEntity<?> getAllEvents(@RequestParam(required = false) String venue, @RequestParam(required = false) String date,
                                          @RequestParam(required = false) String tag) {

        List<EventDto> events =
                eventService
                        .getAllEvents()
                        .stream()
                        .filter(venue != null ? e ->
                                eventService.getEventsByVenueName(venue)
                                        .stream().anyMatch(other -> other.getId().equals(e.getId()))
                                : e -> true)
                        .filter(date != null ? e ->
                                eventService.getEventsByDate(date)
                                        .stream().anyMatch(other -> other.getId().equals(e.getId())) : e -> true)
                        .filter(tag != null ? e ->
                                eventService.getEventsByTag(tag)
                                        .stream().anyMatch(other -> other.getId().equals(e.getId())) : e -> true)
                        .collect(Collectors.toList());

        return new ResponseEntity<>(
                events,
                HttpStatus.OK
        );
    }

    @PostMapping("/events")
    public ResponseEntity<?> createEvent(@Valid @RequestBody EventCreateDto newEvent) {
        return new ResponseEntity<>(
                eventService.createEvent(newEvent),
                HttpStatus.OK
        );
    }

    @GetMapping("/events/{id}")
    public ResponseEntity<?> getEventById(@PathVariable(value = "id") Long eventId) {
        return new ResponseEntity<>(
                eventService.getEventById(eventId),
                HttpStatus.OK
        );
    }

    @PutMapping("events/{id}")
    @PreAuthorize("@creatorCheck.checkIfOwner(#eventId,authentication)|| hasRole('ADMIN')")
    public ResponseEntity<?> updateEventById(@Valid @RequestBody EventUpdateDto updateEvent,
                                             @PathVariable(value = "id") Long eventId) {
        return new ResponseEntity<>(
                eventService.updateEventById(updateEvent, eventId),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/events/{id}")
    @PreAuthorize("@creatorCheck.checkIfOwner(#eventId,authentication)|| hasRole('ADMIN')")
    public void deleteEventById(@PathVariable(value = "id") Long eventId) {
        eventService.deleteEventById(eventId);
    }
}