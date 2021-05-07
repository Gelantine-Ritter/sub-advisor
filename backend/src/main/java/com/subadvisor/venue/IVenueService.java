package com.subadvisor.venue;

import java.util.List;

public interface IVenueService {
    List<Venue> getAllVenues();

    Venue createVenue(Venue venue);

    Venue getVenueById(Long venueId);

    void deleteVenueById(Long venueId);

    Venue updateVenueById(Venue newVenue, Long venueId);

    Venue findByName(String name);

}
