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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

@Log4j2
@Configuration
class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(VenueRepository venueRepository, MemberRepository memberRepository, EventRepository eventRepository) {
        return args -> {

            Venue trudeRuth = venueRepository.save(Venue.builder()
                    .username("trude-ruth")
                    .password("rudiruth")
                    .name("Trude Ruth")
                    .email("ruth@info.com")
                    .info("nette tanzabende")
                    .mobile("0189-28347619")
                    .tags(Set.of("Neukölln", "Bar", "Lokales Bier", "Tischtennis", "Kollektiv"))
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

            Venue hochbeetGarten = venueRepository.save(
                    Venue.builder()
                            .username("hochhinaus")
                            .password("hochhoch")
                            .name("Hochbeet Wedding")
                            .email("hochbeet@info.com")
                            .tags(Set.of("Wedding", "Garten", "Open", "Essen", "Trinken", "Kinderfreundlich"))
                            .info("Ein schöner Garten direkt am Leopoldplatz. Hier kann man entspannen, on der Sonne sitzen" +
                                    "und leckere Salate direkt aus dem eigenen Garten essen.")
                            .mobile("030-198754")
                            .hours(
                                    Map.of("monday", "closed",
                                            "tuesday", "10:00 - 18:00",
                                            "wednesday", "10:00 - 18:00",
                                            "thursday", "10:00 - 18:00",
                                            "friday", "10:00 - 18:00",
                                            "saturday", "10:00 - 18:00",
                                            "sunday", "10:00 - 18:00"
                                    )
                            )
                            .address(
                                    Map.of(
                                            "street", "Leopoldplatz",
                                            "number", "1",
                                            "city", "Berlin",
                                            "plz", "13347"
                                    )
                            )
                            .website("https://hochbeet-wedding.com")
                            .build()
            );


            // Load Venues
            log.info("Preloading " + trudeRuth);
            log.info("Preloading " + hochbeetGarten);

            Event eventTrudeRuth01 = eventRepository.save(
                    Event.builder()
                            .title("Tischtennis am Mittwoch")
                            .tags(Set.of("Sport", "Tischtennis"))
                            .venue(trudeRuth)
                            .eventStart(LocalDateTime.parse("2021-09-04T17:00:00"))
                            .eventEnd(LocalDateTime.parse("2021-09-17T19:00"))
                            .date(LocalDateTime.parse("2021-09-17T19:00").toLocalDate())
                            .info("nettes indoor-tischtennis im Hinterraum")
                            .artists(Set.of("Timo Boll"))
                            .price(10)
                            .build()
            );

            Event eventTrudeRuth02 = eventRepository.save(
                    Event.builder()
                            .title("Salsa tanzen am Dienstag")
                            .venue(trudeRuth)
                            .info("schön Salsa tanzen am Dienstag")
                            .tags(Set.of("Sport", "Tanzen", "Gratis"))
                            .artists(Set.of("DJ Salsa", "MC Salsa"))
                            .eventStart(LocalDateTime.parse("2021-09-07T17:00:00"))
                            .eventEnd(LocalDateTime.parse("2021-09-07T19:00"))
                            .date(LocalDateTime.parse("2021-09-07T19:00").toLocalDate())
                            .price(0)
                            .build()
            );

            Event eventTrudeRuth03 = eventRepository.save(
                    Event.builder()
                            .title("Küfa von Blutgruppe Mayo")
                            .venue(trudeRuth)
                            .info("Jeden Abend gibt es bei uns leckere Hotdogs von der gruppe Blutgruppe Mayo")
                            .tags(Set.of("Essen", "Küfa", "Fastfood"))
                            .artists(Set.of("Blutgruppe Mayo"))
                            .eventStart(LocalDateTime.now())
                            .eventEnd(LocalDateTime.now())
                            .date(LocalDate.now())
                            .price(0)
                            .build()
            );

            Event hochbeet01 = eventRepository.save(
                    Event.builder()
                            .title("Jede:r hat einen grünen Daumen")
                            .venue(hochbeetGarten)
                            .eventStart(LocalDateTime.parse("2021-08-07T17:00:00"))
                            .eventEnd(LocalDateTime.parse("2021-08-07T19:00"))
                            .date(LocalDateTime.parse("2021-08-07T19:00").toLocalDate())
                            .info("alle haben mal klein angefangen! Unsere profi-gaertner*innen Samuel und Nayla zeigen wie ihr" +
                                    "auch in eurer Wohnung schoene Kartoffeln anbauen koennt")
                            .tags(Set.of("Workshop", "Draußen", "Pflanzen"))
                            .artists(Set.of("Nalya Hammerschlag", "Samuel Rosenwald"))
                            .price(5)
                            .build()
            );

            Event hochbeet02 = eventRepository.save(
                    Event.builder()
                            .title("Salatchips selber machen")
                            .venue(hochbeetGarten)
                            .info("In Unserer Offenen Kueche macht Haribert immer einmal in der Woche Salatchips. " +
                                    "Kommt gerne rum")
                            .tags(Set.of("Workshop", "Kochen", "Pflanzen"))
                            .eventStart(LocalDateTime.parse("2021-06-07T17:00:00"))
                            .eventEnd(LocalDateTime.parse("2021-06-07T19:00"))
                            .date(LocalDateTime.parse("2021-06-07T19:00").toLocalDate())
                            .artists(Set.of("Chief of cuisine - Haribert"))
                            .price(0)
                            .build()
            );


            // Load Venues
            log.info("Preloading " + eventTrudeRuth01);

            log.info("Preloading " + eventTrudeRuth02);

            log.info("Preloading " + eventTrudeRuth03);

            log.info("Preloading " + hochbeet01);

            log.info("Preloading " + hochbeet02);

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
                            .username("samsam-supergeheim")
                            .firstName("Sams")
                            .lastName("Goldschmied")
                            .password("goldi")
                            .events(Set.of(hochbeet01, eventTrudeRuth02))
                            .build())
            );
        };
    }
}
