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

            Venue trudeRuth = venueRepository.save(Venue.builder()
                    .username("trude-ruth")
                    .password("rudiruth")
                    .name("Trude Ruth")
                    .email("ruth@info.com")
                    .info("nette tanzabende direkt um die Ecke oder einfach nur ein kühles Rollbier. Geht alles hier auch Salsaabende.")
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

            Venue menschmeier = venueRepository.save(Venue.builder()
                    .username("mensch-meier")
                    .password("müllermüller")
                    .name("Mensch Meier")
                    .email("meier@info.com")
                    .info("Mensch Meier ist ein Klubkollektiv im Osten der Stadt nähe der Landsberger Allee")
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
                                    "street", "Storkowerstrasse",
                                    "number", "121",
                                    "city", "Berlin",
                                    "plz", "10407"
                            )
                    )
                    .website("https://mensch-meier.com")
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
                            .title("Tischtennis am Freitag")
                            .tags(Set.of("Sport", "Tischtennis"))
                            .venue(trudeRuth)
                            .eventStart(LocalDateTime.parse("2021-07-16T20:00"))
                            .eventEnd(LocalDateTime.parse("2021-07-16T20:00").plusHours(2))
                            .date(LocalDateTime.parse("2021-07-16T20:00").toLocalDate())
                            .info("Tischtennis am Mittwoch mit ein bisschen Livemusik. Lasst die Kellen schwingen und tobt euch aus." +
                                    "Dazu gibts wie immer gute Musik und frisch gezapftes Bier.")
                            .artists(Set.of("Timo Boll"))
                            .price(10)
                            .build()
            );

            Event eventTrudeRuth02 = eventRepository.save(
                    Event.builder()
                            .title("Salsa tanzen am Samstag")
                            .venue(trudeRuth)
                            .info("Live Salsamusik mit DJ Salsa und MC Salsa.")
                            .tags(Set.of("indoor", "dj", "house"))
                            .artists(Set.of("DJ Salsa", "MC Salsa"))
                            .eventStart(LocalDateTime.parse("2021-07-17T20:00"))
                            .eventEnd(LocalDateTime.parse("2021-07-17T20:00").plusHours(2))
                            .date(LocalDateTime.parse("2021-07-17T20:00").toLocalDate())
                            .price(0)
                            .build()
            );

            Event eventTrudeRuth03 = eventRepository.save(
                    Event.builder()
                            .title("Küfa von Blutgruppe Mayo")
                            .venue(trudeRuth)
                            .info("Lecker Fastfood von Blurgrupe Mayo gegen Spende. Pommes, vegane Currywurst und natürlich frittierte Spätzle")
                            .tags(Set.of("outside", "conversation", "political"))
                            .artists(Set.of("Blutgruppe Mayo"))
                            .eventStart(LocalDateTime.parse("2021-07-18T20:00"))
                            .eventEnd(LocalDateTime.parse("2021-07-18T20:00").plusHours(2))
                            .date(LocalDateTime.parse("2021-07-18T20:00").toLocalDate())
                            .price(0)
                            .build()
            );

            Event eventTrudeRuth04 = eventRepository.save(
                    Event.builder()
                            .title("Punk am Sonntag")
                            .venue(trudeRuth)
                            .info("Punk am Sonntag lädt ein mit einer Auswahl an netten Bands.")
                            .tags(Set.of("punk", "dj", "political", "inside"))
                            .artists(Set.of("Krimewatch", "The Distillers", "Bikini Kill"))
                            .eventStart(LocalDateTime.parse("2021-07-18T22:00"))
                            .eventEnd(LocalDateTime.parse("2021-07-18T22:00").plusHours(2))
                            .date(LocalDateTime.parse("2021-07-18T20:00").toLocalDate())
                            .price(0)
                            .build()
            );

            Event hochbeet01 = eventRepository.save(
                    Event.builder()
                            .title("Jede:r hat einen grünen Daumen")
                            .venue(hochbeetGarten)
                            .eventStart(LocalDateTime.parse("2021-07-16T14:00"))
                            .eventEnd(LocalDateTime.parse("2021-07-16T14:00").plusHours(2))
                            .date(LocalDateTime.parse("2021-07-16T20:00").toLocalDate())
                            .info("Alle können Gärtnern, dabei helfen Nayla und Samuel im Hochbeet-Garten.")
                            .tags(Set.of("outside", "gärtnern", "contemporary"))
                            .artists(Set.of("Nalya Hammerschlag", "Samuel Rosenwald"))
                            .price(5)
                            .build()
            );

            Event hochbeet02 = eventRepository.save(
                    Event.builder()
                            .title("Salatchips selber machen")
                            .venue(hochbeetGarten)
                            .info("Haribert zeigt dass es ganz einfach ist Salatchips selber herzustellen.")
                            .tags(Set.of("Workshop", "Kochen", "Pflanzen"))
                            .eventStart(LocalDateTime.parse("2021-07-16T16:00"))
                            .eventEnd(LocalDateTime.parse("2021-07-16T16:00").plusHours(2))
                            .date(LocalDateTime.parse("2021-07-16T20:00").toLocalDate())
                            .artists(Set.of("Chief of cuisine - Haribert"))
                            .price(0)
                            .build()
            );

            Event eventMenschMeier01 = eventRepository.save(
                    Event.builder()
                            .title("Synobs label release")
                            .tags(Set.of("techno", "acid", "dj", "inside"))
                            .venue(menschmeier)
                            .eventStart(LocalDateTime.parse("2021-07-16T23:59"))
                            .eventEnd(LocalDateTime.parse("2021-07-16T23:59").plusHours(12))
                            .date(LocalDateTime.parse("2021-07-16T20:00").toLocalDate())
                            .info("Synobs label lädt ein für die nächste release-party.")
                            .artists(Set.of("Alberto Mignani", "DJ Jim Bob", "Amina de cruiso"))
                            .price(10)
                            .build()
            );

            Event eventMenschMeier02 = eventRepository.save(
                    Event.builder()
                            .title("Unterm Techno liegt der Punk")
                            .tags(Set.of("techno", "punk", "dj", "inside"))
                            .venue(menschmeier)
                            .eventStart(LocalDateTime.parse("2021-07-17T23:59"))
                            .eventEnd(LocalDateTime.parse("2021-07-17T23:59").plusHours(24))
                            .date(LocalDateTime.parse("2021-07-17T20:00").toLocalDate())
                            .info("Unterm Techno liegt der Punkt zeigt musikalisch den Zusammenhang von Punkt und Techno")
                            .artists(Set.of("DJ Rotzevoll", "Sterni 3000", "Ostkreuz radikal"))
                            .price(10)
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
                            .firstName("Jimmy")
                            .lastName("bleu")
                            .email("jimmy.bleu@gmail.com")
                            .password("supercool")
                            .events(Set.of(hochbeet01, eventTrudeRuth02, eventTrudeRuth04, eventTrudeRuth03))
                            .enabled(true)
                            .build())
            );

            log.info("Preloading " + memberRepository.save(
                    Member.builder()
                            .username("SamSam123")
                            .firstName("Sam")
                            .lastName("Goldschmied")
                            .password("goldi")
                            .events(Set.of(hochbeet01, eventTrudeRuth02, eventTrudeRuth04, eventTrudeRuth03, eventMenschMeier01, eventMenschMeier02))
                            .build())
            );

            log.info("Preloading " + memberRepository.save(
                    Member.builder()
                            .username("Nayla-34")
                            .firstName("Nayla")
                            .lastName("Hammerschlag")
                            .password("hammer")
                            .events(Set.of(hochbeet01, eventTrudeRuth02, eventTrudeRuth04, eventTrudeRuth03, eventMenschMeier01, eventMenschMeier02))
                            .build())
            );

            log.info("Preloading " + memberRepository.save(
                    Member.builder()
                            .username("fridalina")
                            .firstName("Frida")
                            .lastName("Petersen")
                            .password("peter")
                            .events(Set.of(hochbeet02, eventTrudeRuth02, eventTrudeRuth04, eventTrudeRuth03, eventMenschMeier01, eventMenschMeier02))
                            .build())
            );
        };
    }
}
