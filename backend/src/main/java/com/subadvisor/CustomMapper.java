package com.subadvisor;

import com.subadvisor.api.auth.dto.VenueRegistrateDto;
import com.subadvisor.api.event.Event;
import com.subadvisor.api.event.dto.EventCreateDto;
import com.subadvisor.api.event.dto.EventDto;
import com.subadvisor.api.event.dto.EventUpdateDto;
import com.subadvisor.api.member.Member;
import com.subadvisor.api.member.dto.MemberDto;
import com.subadvisor.api.member.dto.MemberRegistrateDto;
import com.subadvisor.api.member.dto.MemberUpdateDto;
import com.subadvisor.api.venue.Venue;
import com.subadvisor.api.venue.dto.VenuePersonalDto;
import com.subadvisor.api.venue.dto.VenuePublicDto;
import com.subadvisor.api.venue.dto.VenueUpdateDto;
import org.mapstruct.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

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
    @Mapping(source = "members", target = "amountOfGuests", qualifiedByName = "mapMembersToAmount")
    @Mapping(source = "members", target = "guests", qualifiedByName = "mapMembersToHashMap")
    EventDto eventToEventDto(Event event);

    @Named("mapMembersToHashMap")
    default String[] mapMembersToHashMap(Set<Member> members) {
        if (members == null) return null;
        List<String> listOfGuests = new ArrayList<>();
        members.forEach(
                member ->
                        listOfGuests.add(member.getUsername())
        );
        return listOfGuests.toArray(new String[0]);
    }

    @Named("mapMembersToAmount")
    default int mapMembersToAmount(Set<Member> members) {
        if (members == null) return 0;
        return members.size();
    }

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


    /**
     * Member
     **/

    Member memberRegistrateDtoToMember(MemberRegistrateDto memberRegistrateDto);

    @Mapping(source = "pic", target = "pic", qualifiedByName = "byteToBase64")
    @Mapping(source = "events", target = "events", qualifiedByName = "eventToEventMap")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    MemberDto memberToMemberDto(Member member);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "pic", target = "pic", qualifiedByName = "base64ToByte")
    Member memberUpdateDtoToMember(MemberUpdateDto memberUpdateDto, @MappingTarget Member member);

    @Named("eventToEventMap")
    default Map<String, String> eventToEventMap(Set<Event> events) {
        if (events == null) return null;
        Map<String, String> eventMap = new HashMap<>();
        events.forEach(
                event -> eventMap.put(
                        event.getId().toString(),
                        event.getTitle()
                )
        );
        return eventMap;
    }
}
