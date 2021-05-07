package com.subadvisor.venue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
public class VenueController {

    @Autowired
    VenueService venueService;

    @GetMapping("/venues")
    public List<Venue> getAllVenues(){
        return venueService.getAllVenues();
    }

    @PostMapping(path = "/venues")
    public void createVenue (@RequestBody Venue venue) {
        venueService.createVenue(venue);
    }

    @GetMapping("/venues/{id}")
    public Venue getVenueById(@PathVariable(value="id") Long venueId){
        return venueService.getVenueById(venueId);
    }

    @DeleteMapping("/venues/{id}")
    public void deleteVenueById(@PathVariable(value="id") Long venueId){
        venueService.deleteVenueById(venueId);
    }

    @PutMapping("/venues/{id}")
    public void updateVenueById(@RequestBody Venue newVenue, @PathVariable Long id) {
        venueService.updateVenueById(newVenue, id);
    }
}
