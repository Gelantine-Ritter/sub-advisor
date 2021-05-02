package com.subadvisor.backend.controller;

import com.subadvisor.backend.model.Venue;
import com.subadvisor.backend.service.IVenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
public class VenueController {

    @Autowired
    IVenueService venueService;

    @GetMapping("/venues")
    @ResponseBody
    public List<Venue> getAllVenues(){
        return venueService.getAllVenues();
    }

    @PostMapping("/venues")
    @ResponseBody
    public void createVenue ( Venue venue) {
        venueService.createVenue(venue);
    }

    @GetMapping("/venues/{id}")
    @ResponseBody
    public Venue getVenueById(@PathVariable(value="id") Long venueId){
        Optional<Venue> venue_opt = venueService.getVenueById(venueId);
        if (venue_opt.isPresent()){
            return venue_opt.get();
        }
        return null;
    }

    @DeleteMapping("/venues/{id}")
    @ResponseBody
    public void deleteVenueById(@PathVariable(value="id") Long venueId){
        venueService.deleteVenueById(venueId);
    }
}
