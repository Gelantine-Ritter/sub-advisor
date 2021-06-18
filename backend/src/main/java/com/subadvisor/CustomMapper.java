package com.subadvisor;
import com.subadvisor.api.auth.dto.VenueRegistrateDto;
import com.subadvisor.api.event.Event;
import com.subadvisor.api.event.dto.EventCreateDto;
import com.subadvisor.api.event.dto.EventDto;
import com.subadvisor.api.event.dto.EventUpdateDto;
import com.subadvisor.api.venue.Venue;
import com.subadvisor.api.venue.dto.VenuePersonalDto;
import com.subadvisor.api.venue.dto.VenuePublicDto;
import com.subadvisor.api.venue.dto.VenueUpdateDto;
import org.mapstruct.*;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface CustomMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source="pic", target="pic", qualifiedByName = "base64ToByte")
    Venue venueUpdateDtoToVenue(VenueUpdateDto venueUpdateDto, @MappingTarget Venue venue);

    Venue venueRegistrateDtoToVenue(VenueRegistrateDto venueRegistrateDto);

    @Named("base64ToByte")
    default byte[] base64ToByte(String pic) {
        return pic != null ? pic.getBytes() : null;
    }

    @Mapping(source="pic", target="pic", qualifiedByName = "byteToBase64" )
    VenuePersonalDto venueToVenuePersonalDto(Venue venue);

    @Mapping(source="pic", target="pic", qualifiedByName = "byteToBase64" )
    VenuePublicDto venueToVenuePublicDto(Venue venue);

    @Named("byteToBase64")
    default String byteToBase64(byte[] pic) {
        return pic != null ? new String(pic) : null;
    }

    @Mapping(target = "venueId", source = "event.venue.id")
    EventDto eventToEventDto(Event event);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void eventUpdateDtoToEvent(EventUpdateDto eventUpdateDto, @MappingTarget Event event);

    Event eventCreateDtoToEvent(EventCreateDto eventCreateDto);
}
