package com.subadvisor.api.venue;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.subadvisor.api.auth.dto.VenueRegistrateDto;
import com.subadvisor.api.venue.dto.IVenueDto;
import com.subadvisor.api.venue.dto.VenuePersonalDto;
import com.subadvisor.api.venue.dto.VenuePublicDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
public class VenueService implements UserDetailsService, IVenueService {

    @Autowired
    VenueRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public List<VenuePublicDto> getAllVenues() {
        return repository.findAll()
                .stream()
                .map(
                        venue -> objectMapper.convertValue(
                                venue,
                                VenuePublicDto.class
                        )
                ).collect(Collectors.toList());

    }

    @Override
    public VenuePersonalDto createVenue(VenueRegistrateDto venueRegistrateDto) {
        return objectMapper.convertValue(
                repository.save(
                        objectMapper.convertValue(
                                venueRegistrateDto,
                                Venue.class
                        )),
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
                                                .password(newVenue.password())
                                                .name(newVenue.name())
                                                .email(newVenue.email())
                                                .info(newVenue.info())
                                                .hours(newVenue.hours())
                                                .mobile(newVenue.mobile())
                                                .website(newVenue.website())
                                                .address(newVenue.address())
                                                .pic(newVenue.pic())
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
