package com.subadvisor.venue;

import java.util.List;

public interface IVenueService {
    List<Venue>getAllVenues();
    void createVenue(Venue venue);
    Venue getVenueById(Long venueId);
    void deleteVenueById(Long venueId);
    void updateVenueById(Venue newVenue, Long venueId);
}
