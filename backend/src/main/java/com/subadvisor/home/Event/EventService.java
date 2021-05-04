package com.subadvisor.backend.Event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService implements IEventService {

    @Autowired
    EventRepository repository;

    @Override
    public List<Event> getAllEvents(){
        return repository.findAll();
    }
    @Override
    public void createEvent(Event event) {
        repository.save(event);
    }

    @Override
    public Optional<Event> getEventById(Long eventId){
        return repository.findById(eventId);
    }

    @Override
    public void deleteEventById(Long eventId) {
        repository.deleteById(eventId);
    }
}
