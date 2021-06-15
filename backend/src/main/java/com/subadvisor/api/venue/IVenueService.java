package com.subadvisor.api.venue;

import com.subadvisor.api.auth.dto.VenueRegistrateDto;
import com.subadvisor.api.venue.dto.IVenueDto;
import com.subadvisor.api.venue.dto.VenuePersonalDto;
import com.subadvisor.api.venue.dto.VenuePublicDto;
import com.subadvisor.api.venue.dto.VenueUpdateDto;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface IVenueService {
    List<VenuePublicDto> getAllVenues();

    VenuePersonalDto createVenue(VenueRegistrateDto venueRegistrateDto);

    IVenueDto getVenueById(Authentication authentication, Long venueId);

    void deleteVenueById(Long venueId);

    VenuePersonalDto updateVenueById(VenueUpdateDto venueUpdateDto, Long venueId);

    Venue findByName(String name);

}
