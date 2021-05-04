package com.subadvisor.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
public class EventController {

    @Autowired
    IEventService eventService;

    @GetMapping("/events/")
    public List<Event> getAllEvents() {

        return eventService.getAllEvents();
    }

    @PostMapping("/events/")
    public void createEvent(Event event) {
        eventService.createEvent(event);
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
