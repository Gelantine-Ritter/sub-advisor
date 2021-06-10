package com.subadvisor;

import com.subadvisor.api.event.Event;
import com.subadvisor.api.event.EventRepository;
import com.subadvisor.api.member.Member;
import com.subadvisor.api.member.MemberRepository;
import com.subadvisor.api.venue.Venue;
import com.subadvisor.api.venue.VenueRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

@Log4j2
@Configuration
class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(VenueRepository venueRepository, MemberRepository memberRepository, EventRepository eventRepository) {
        return args -> {

            Venue trudeRuth = venueRepository.save(
                    Venue.builder()
                            .username("trude-ruth")
                            .password("rudiruth")
                            .name("Trude Ruth")
                            .email("ruth@info.com")
                            .info("nette tanzabende")
                            .mobile("0189-28347619")
                            .hours(
                                    Map.of("monday", "closed",
                                            "tuesday", "17:00 - 23:00",
                                            "wednesday", "17:00 - 23:00",
                                            "thursday", "17:00 - 23:00",
                                            "friday", "17:00 - 02:00",
                                            "saturday", "17:00 - 02:00",
                                            "sunday", "17:00 - 02:00"
                                    )
                            )
                            .address(
                                    Map.of(
                                            "street", "Flughafenstrasse",
                                            "number", "38",
                                            "city", "Berlin",
                                            "plz", "12053"
                                    )
                            )
                            .website("https://trude-ruth.com")
                            .build()
            );


            // Load Venues
            log.info("Preloading " + trudeRuth);

            Event eventTrudeRuth = eventRepository.save(
                    Event.builder()
                    .title("Tischtennis am Mittwoch")
                    .venue(trudeRuth)
                    .info("nettes indoor-tischtennis im Hinterraum")
                    .artists(Set.of("Timo Boll"))
                    .price(10)
                    .build()
            );



            // Load Venues
            log.info("Preloading " + eventTrudeRuth);

            // Load TestUser
            log.info("Preloading " + memberRepository.save(
                    Member.builder()
                            .username("Jimmy-Bleu")
                            .password("supercool")
                            .enabled(true)

                            .build())
            );

            log.info("Preloading " + memberRepository.save(
                    Member.builder()
                            .username("Nayla Thalhammer")
                            .password("hammerschlag")
                            .build())
            );
        };
    }
}
