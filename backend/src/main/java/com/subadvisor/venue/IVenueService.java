package com.subadvisor.venue;

import java.util.List;
import java.util.Optional;

public interface IVenueService {
    List<Venue>getAllVenues();
    void createVenue(Venue venue);
    Optional<Venue> getVenueById(Long venueId);
    void deleteVenueById(Long venueId);
}
