package com.subadvisor.api.venue;

import com.subadvisor.api.venue.dto.VenuePersonalDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
public class VenueController {

    @Autowired
    VenueService venueService;

    @GetMapping("/venues")
    public List<Venue> getAllVenues() {
        return venueService.getAllVenues();
    }

    @PostMapping(path = "/venues")
    public ResponseEntity<?> createVenue(@RequestBody Venue venue) {
        log.debug("try to create venue");

        return new ResponseEntity<>(
                venueService.createVenue(venue),
                HttpStatus.OK
        );
    }

    @GetMapping("/venues/{id}")
    public Venue getVenueById(@PathVariable(value = "id") Long venueId) {
        return venueService.getVenueById(venueId);
    }

    @DeleteMapping("/venues/{id}")
    public void deleteVenueById(@PathVariable(value = "id") Long venueId) {
        venueService.deleteVenueById(venueId);
    }

    @PutMapping("/venues/{id}")
    @PreAuthorize("authentication.principal.username == #username || hasRole('ADMIN')")
    public Venue updateVenueById(@RequestBody Venue newVenue, @PathVariable Long id) {
        return venueService.updateVenueById(newVenue, id);
    }
}
