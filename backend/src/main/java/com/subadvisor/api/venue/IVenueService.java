package com.subadvisor.api.venue;

import com.subadvisor.api.venue.dto.IVenueDto;
import com.subadvisor.api.venue.dto.VenuePersonalDto;
import com.subadvisor.api.venue.dto.VenuePublicDto;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface IVenueService {
    List<VenuePublicDto> getAllVenues();

    VenuePersonalDto createVenue(Venue venue);

    IVenueDto getVenueById(Authentication authentication, Long venueId);

    void deleteVenueById(Long venueId);

    IVenueDto updateVenueById(Venue newVenue, Long venueId);

    Venue findByName(String name);

}
