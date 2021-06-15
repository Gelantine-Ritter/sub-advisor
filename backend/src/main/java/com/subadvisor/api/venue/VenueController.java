package com.subadvisor.api.venue;


import com.subadvisor.api.venue.dto.VenuePersonalDto;
import com.subadvisor.api.venue.dto.VenuePublicDto;
import com.subadvisor.api.venue.dto.VenueUpdateDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
public class VenueController {

    @Autowired
    VenueService venueService;

    @GetMapping("/venues")
    public ResponseEntity<List<VenuePublicDto>> getAllVenues() {
        return new ResponseEntity<>(
                venueService.getAllVenues(),
                HttpStatus.OK
        );
    }

    @GetMapping("/venues/{id}")
    public ResponseEntity<?>  getVenueById(@PathVariable(value = "id") Long venueId,
                                                                           Authentication authentication) {
        return new ResponseEntity<>(
                venueService.getVenueById(authentication, venueId),
                HttpStatus.OK
        );
    }

    @PutMapping("/venues/{id}")
    @PreAuthorize("authentication.principal.id == #id || hasRole('ADMIN')")
    public ResponseEntity<VenuePersonalDto> updateVenueById(@RequestBody VenueUpdateDto venueUpdateDto, @PathVariable Long id) {
        return new ResponseEntity<>(
                venueService.updateVenueById(venueUpdateDto, id),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/venues/{id}")
    @PreAuthorize("authentication.principal.id == #id || hasRole('ADMIN')")
    public void deleteVenueById(@PathVariable Long id, Authentication authentication) {
        venueService.deleteVenueById(id);
    }
}
