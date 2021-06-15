package com.subadvisor;
import com.subadvisor.api.event.Event;
import com.subadvisor.api.event.dto.EventDto;
import com.subadvisor.api.event.dto.EventUpdateDto;
import com.subadvisor.api.venue.Venue;
import com.subadvisor.api.venue.dto.VenuePersonalDto;
import com.subadvisor.api.venue.dto.VenueUpdateDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CustomMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void venueUpdateDtoToVenue(VenueUpdateDto venueUpdateDto, @MappingTarget Venue venue);
    VenuePersonalDto venueToVenuePersonalDto(Venue venue);

    @Mapping(target = "venueId", source = "event.venue.id")
    EventDto eventToEventDto(Event event);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void eventUpdateDtoToEvent(EventUpdateDto eventUpdateDto, @MappingTarget Event event);
}
