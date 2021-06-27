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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface CustomMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "pic", target = "pic", qualifiedByName = "base64ToByte")
    Venue venueUpdateDtoToVenue(VenueUpdateDto venueUpdateDto, @MappingTarget Venue venue);

    Venue venueRegistrateDtoToVenue(VenueRegistrateDto venueRegistrateDto);

    @Named("base64ToByte")
    default byte[] base64ToByte(String pic) {
        return pic != null ? pic.getBytes() : null;
    }

    @Mapping(source = "pic", target = "pic", qualifiedByName = "byteToBase64")
    VenuePersonalDto venueToVenuePersonalDto(Venue venue);

    @Mapping(source = "pic", target = "pic", qualifiedByName = "byteToBase64")
    VenuePublicDto venueToVenuePublicDto(Venue venue);

    @Named("byteToBase64")
    default String byteToBase64(byte[] pic) {
        return pic != null ? new String(pic) : null;
    }

    @Mapping(target = "venueId", source = "event.venue.id")
    @Mapping(source = "pic", target = "pic", qualifiedByName = "byteToBase64")
    EventDto eventToEventDto(Event event);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "pic", target = "pic", qualifiedByName = "base64ToByte")
    @Mapping(target = "date", source = "eventUpdateDto.eventStart", qualifiedByName = "localDateTimeToLocalDate")
    void eventUpdateDtoToEvent(EventUpdateDto eventUpdateDto, @MappingTarget Event event);

    @Mapping(source = "eventCreateDto.pic", target = "pic", qualifiedByName = "base64ToByte")
    @Mapping(source = "venue", target = "venue")
    @Mapping(source = "eventCreateDto.info", target = "info")
    @Mapping(source = "eventCreateDto.tags", target = "tags")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "date", source = "eventCreateDto.eventStart", qualifiedByName = "localDateTimeToLocalDate")
    Event eventCreateDtoToEvent(EventCreateDto eventCreateDto, Venue venue);

    @Named("localDateTimeToLocalDate")
    default LocalDate localDateTimeToLocalDate(LocalDateTime eventStart) {
        return eventStart != null ? eventStart.toLocalDate() : null;
    }

}
