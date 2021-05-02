package com.subadvisor.backend.service;

import com.subadvisor.backend.model.Venue;
import java.util.List;
import java.util.Optional;

public interface IVenueService {
    List<Venue>getAllVenues();
    void createVenue(Venue venue);
    Optional<Venue> getVenueById(Long venueId);
    void deleteVenueById(Long venueId);
}
