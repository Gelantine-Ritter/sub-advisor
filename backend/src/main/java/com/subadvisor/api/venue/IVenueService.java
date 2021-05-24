package com.subadvisor.api.venue;

import com.subadvisor.api.venue.dto.VenuePersonalDto;

import java.util.List;

public interface IVenueService {
    List<Venue> getAllVenues();

    VenuePersonalDto createVenue(Venue venue);

    Venue getVenueById(Long venueId);

    void deleteVenueById(Long venueId);

    Venue updateVenueById(Venue newVenue, Long venueId);

    Venue findByName(String name);

}
