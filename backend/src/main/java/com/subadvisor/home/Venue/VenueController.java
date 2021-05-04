package com.subadvisor.backend.Venue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
public class VenueController {

    @Autowired
    IVenueService venueService;

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
        Optional<Venue> venue_opt = venueService.getVenueById(venueId);
        if (venue_opt.isPresent()){
            return venue_opt.get();
        }
        return null;
    }

    @DeleteMapping("/venues/{id}")
    public void deleteVenueById(@PathVariable(value="id") Long venueId){
        venueService.deleteVenueById(venueId);
    }
}
