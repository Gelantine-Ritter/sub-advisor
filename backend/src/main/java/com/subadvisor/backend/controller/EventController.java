package com.subadvisor.backend.controller;

import com.subadvisor.backend.model.Event;
import com.subadvisor.backend.service.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/events")
public class EventController {

    @Autowired
    IEventService eventService;

    @GetMapping("/")
    @ResponseBody
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @PostMapping("/")
    @ResponseBody
    public void createEvent(Event event) {
        eventService.createEvent(event);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Event getEventById(@PathVariable(value = "id") Long eventId){
        Optional<Event> event_opt = eventService.getEventById(eventId);
        if (event_opt.isPresent()){
            return event_opt.get();
        }else{
            return null;
        }
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public void deleteEventById(@PathVariable(value="id") Long eventId){
        eventService.deleteEventById(eventId);
    }

}
