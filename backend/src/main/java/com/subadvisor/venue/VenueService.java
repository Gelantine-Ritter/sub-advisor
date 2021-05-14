package com.subadvisor.venue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static java.lang.String.format;

@Service
public class VenueService implements UserDetailsService, IVenueService {

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
                                .name(newVenue.name())
                                .email(newVenue.email())
                                .info(newVenue.info())
                        ))
                .orElseGet(() ->
                        repository.save(
                                newVenue.id(venueId)
                        )
                );
    }

    @Override
    public Venue findByName(String name) {

        return repository.findByName(name);

    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return repository
                .findByUsername(s)
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                format("User with username - %s, not found", s)
                        )
                );
    }
}
