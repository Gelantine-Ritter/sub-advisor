package com.subadvisor;
import com.subadvisor.api.event.Event;
import com.subadvisor.api.event.dto.EventDto;
import com.subadvisor.api.venue.Venue;
import com.subadvisor.api.venue.dto.VenuePersonalDto;
import com.subadvisor.api.venue.dto.VenueUpdateDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface CustomMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void venueUpdateDtoToVenue(VenueUpdateDto venueUpdateDto, @MappingTarget Venue venue);
    VenuePersonalDto venueToVenuePersonalDto(Venue venue);
    EventDto eventToEventDto(Event event);
}
