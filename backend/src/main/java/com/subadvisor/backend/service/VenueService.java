package com.subadvisor.backend.service;

import com.subadvisor.backend.model.Venue;
import com.subadvisor.backend.repository.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VenueService implements IVenueService {

    @Autowired
    VenueRepository repository;

    @Override
    public List<Venue> getAllVenues(){
        return repository.findAll();
    }

    @Override
    public void createVenue(Venue venue) {
        repository.save(venue);
    }

    @Override
    public Optional<Venue> getVenueById(Long venueId){
        return repository.findById(venueId);
    }

    @Override
    public void deleteVenueById(Long venueId) {
        repository.deleteById(venueId);
    }
}
