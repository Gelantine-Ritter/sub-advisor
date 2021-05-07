package com.subadvisor.venue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class VenueService implements IVenueService {

    @Autowired
    VenueRepository repository;

    @Override
    public List<Venue> getAllVenues() {
        return repository.findAll();
    }

    @Override
    public Venue createVenue(Venue venue) {
        return repository.save(venue);
    }

    @Override
    public Venue getVenueById(Long venueId) {

        return repository
                .findById(venueId)
                .orElseThrow(() -> new EntityNotFoundException(("No Venue found for the id " + venueId)));
    }

    @Override
    public void deleteVenueById(Long venueId) {
        repository.deleteById(venueId);
    }

    @Override
    public Venue updateVenueById(Venue newVenue, Long venueId) {

        return repository.findById(venueId)
                .map(venue ->
                        repository.save(venue
                                .setName(newVenue.getName())
                                .setEmail(newVenue.getEmail())
                                .setInfo(newVenue.getInfo())
                        ))
                .orElseGet(() ->
                        repository.save(
                                newVenue.setId(venueId)
                        )
                );
    }

    @Override
    public Venue findByName(String name) {

        return repository.findByName(name);

    }
}
