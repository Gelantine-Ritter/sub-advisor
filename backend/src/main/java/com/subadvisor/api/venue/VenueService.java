package com.subadvisor.api.venue;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.subadvisor.api.venue.dto.IVenueDto;
import com.subadvisor.api.venue.dto.VenuePersonalDto;
import com.subadvisor.api.venue.dto.VenuePublicDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static java.lang.String.format;

@Service
public class VenueService implements UserDetailsService, IVenueService {

    @Autowired
    VenueRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public List<Venue> getAllVenues() {
        return repository.findAll();
    }

    @Override
    public VenuePersonalDto createVenue(Venue venue) {

        return objectMapper.convertValue(
                repository.save(venue),
                VenuePersonalDto.class
        );
    }

    @Override
    public IVenueDto getVenueById(Authentication authentication, Long venueId) {

        return repository.findById(venueId)
                .map(venue ->
                        authentication == null ?
                                objectMapper.convertValue(
                                        venue,
                                        VenuePublicDto.class) :
                                ((Venue) authentication.getPrincipal()).id() == venueId ?
                                        objectMapper.convertValue(
                                                venue,
                                                VenuePersonalDto.class
                                        ) : objectMapper.convertValue(
                                        venue,
                                        VenuePublicDto.class)

                ).orElseThrow(
                        () -> new UsernameNotFoundException(
                                format("Venue with it - %s, not found", venueId)
                        )
                );
    }

    @Override
    public void deleteVenueById(Long venueId) {
        repository.deleteById(venueId);
    }

    @Override
    public IVenueDto updateVenueById(Venue newVenue, Long venueId) {

        return objectMapper.convertValue(
                repository.findById(venueId)
                        .map(venue ->
                                repository.save(
                                        venue
                                                .username(newVenue.username())
                                                .password(newVenue.password())
                                                .name(newVenue.name())
                                                .email(newVenue.email())
                                                .info(newVenue.info())
                                )
                        )
                        .orElseGet(() ->
                                repository.save(
                                        newVenue.id(venueId)
                                )
                        ),
                VenuePersonalDto.class);
    }

    @Override
    public Venue findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return repository
                .findByUsername(s)
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                format("User with username - %s, not found", s)
                        )
                );
    }
}
