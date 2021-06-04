package com.subadvisor.api.event;

import com.subadvisor.api.event.dto.EventCreateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
public class EventController {

    @Autowired
    EventService eventService;

    @GetMapping("/events/")
    public List<Event> getAllEvents() {

        return eventService.getAllEvents();
    }

    @PostMapping("/events/")
    public ResponseEntity<?> createEvent(@RequestBody EventCreateDto newEvent) {
        return new ResponseEntity<>(
                eventService.createEvent(newEvent),
                HttpStatus.OK
        );
    }

    @GetMapping("/events/{id}")
    public Event getEventById(@PathVariable(value = "id") Long eventId){
        Optional<Event> event_opt = eventService.getEventById(eventId);
        if (event_opt.isPresent()){
            return event_opt.get();
        }else{
            return null;
        }
    }

    @DeleteMapping("/events/{id}")
    public void deleteEventById(@PathVariable(value="id") Long eventId){
        eventService.deleteEventById(eventId);
    }

}
