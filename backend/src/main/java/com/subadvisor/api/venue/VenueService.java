package com.subadvisor.api.venue;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.subadvisor.CustomMapper;
import com.subadvisor.DataAccess;
import com.subadvisor.api.auth.dto.VenueRegistrateDto;
import com.subadvisor.api.venue.dto.IVenueDto;
import com.subadvisor.api.venue.dto.VenuePersonalDto;
import com.subadvisor.api.venue.dto.VenuePublicDto;
import com.subadvisor.api.venue.dto.VenueUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
public class VenueService implements UserDetailsService, IVenueService {

    @Autowired
    DataAccess DATA;

    @Autowired
    VenueRepository repository;

    @Autowired
    private CustomMapper mapper;

    @Override
    public List<VenuePublicDto> getAllVenues() {
        return repository.findAll()
                .stream()
                .map(venue -> mapper.venueToVenuePublicDto(venue))
                .collect(Collectors.toList());
    }

    @Override
    public VenuePersonalDto createVenue(VenueRegistrateDto venueRegistrateDto) {
        return
                mapper.venueToVenuePersonalDto(
                        repository.save(
                                mapper.venueRegistrateDtoToVenue(venueRegistrateDto)
                        )
                );
    }

    @Override
    public IVenueDto getVenueById(Authentication authentication, Long venueId) {

        return repository.findById(venueId)
                .map(venue -> {
                            if (authentication != null && authentication.getPrincipal() instanceof Venue && ((Venue) authentication.getPrincipal()).getId() == venueId) {
                                return mapper.venueToVenuePersonalDto(venue);
                            } else {
                                return mapper.venueToVenuePublicDto(venue);
                            }
                        }
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
    public VenuePersonalDto updateVenueById(VenueUpdateDto venueUpdateDto, Long venueId) {

        return
                repository.findById(venueId)
                        .map(venue -> mapper.venueUpdateDtoToVenue(venueUpdateDto, venue))
                        .map(venue -> repository.save(venue))
                        .map(venue -> mapper.venueToVenuePersonalDto(venue))
                        .orElseThrow(
                                () -> new UsernameNotFoundException(
                                        format("User with id - %s, not found", venueId)
                                )
                        );
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
