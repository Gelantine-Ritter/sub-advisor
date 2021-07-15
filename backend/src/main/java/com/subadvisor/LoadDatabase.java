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

            Venue tor22 = venueRepository.save(Venue.builder()
                    .username("tor-22")
                    .password("tor22_pw")
                    .name("Tor 22")
                    .email("tor22@info.com")
                    .info("Wer das Tor 22 nicht kennt hat nicht gelernt zu leben. 'Hier wird der Hip Hop noch hoch gehalten', findet auch der Karlshorster Lokalanzeiger. Jeden Samstag Beats aufs Ohr für einen schmalen Taler.")
                    .mobile("0181-23443612")
                    .hours(
                            Map.of("monday", "closed",
                                    "tuesday", "17:00 - 19:00",
                                    "wednesday", "17:00 - 19:00",
                                    "thursday", "17:00 - 19:00",
                                    "friday", "17:00 - 04:00",
                                    "saturday", "17:00 - 04:00",
                                    "sunday", "17:00 - 22:00"
                            )
                    )
                    .address(
                            Map.of(
                                    "street", "Ehrlichstrasse",
                                    "number", "18",
                                    "city", "Berlin",
                                    "plz", "10318"
                            )
                    )
                    .website("https://tor-22.de")
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

            Venue grueneSchatztruhe = venueRepository.save(Venue.builder()
                    .username("grueneschatztruhe")
                    .password("grueneschatztruhe")
                    .name("Grüne Schatztruhe")
                    .email("gruene@schatztruhe.com")
                    .info("Blumen können uns verzaubern. Sie können eine Stimmung ausdrücken und ein Lächeln schenken. Sie können uns in unserer Trauer unterstützen und unsere Emotionen ausdrücken. Blumen können unsere Liebe gestehen oder einfach nur Freude schenken.")
                    .mobile("0181-13242219")
                    .hours(
                            Map.of("monday", "closed",
                                    "tuesday", "10:00 - 19:00",
                                    "wednesday", "10:00 - 19:00",
                                    "thursday", "10:00 - 19:00",
                                    "friday", "10:00 - 17:00",
                                    "saturday", "12:00 - 17:00",
                                    "sunday", "12:00 - 13:00"
                            )
                    )
                    .address(
                            Map.of(
                                    "street", "Lettestrasse",
                                    "number", "6",
                                    "city", "Berlin",
                                    "plz", "10437"
                            )
                    )
                    .website("https://gruene-schatztruhe.de")
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

            Event eventMenschMeier03 = eventRepository.save(
                    Event.builder()
                            .title("Coma 92")
                            .tags(Set.of("new wave", "inside"))
                            .venue(menschmeier)
                            .eventStart(LocalDateTime.parse("2021-07-24T20:00"))
                            .eventEnd(LocalDateTime.parse("2021-07-24T23:30").plusHours(24))
                            .date(LocalDateTime.parse("2021-07-24T20:00").toLocalDate())
                            .info("auf der bühne fesselt der kompakte, ungeschliffene, doch sehr intelligent arrangierte z – sound, für den die band den begriff „berlin wave“ erfand, obwohl z durchaus wie eine englische gruppe klingt. keine musik für irgendeine schublade.")
                            .artists(Set.of("Big Blue Cadillac"))
                            .price(5)
                            .build()
            );

            // =================

            Event tor22_1 = eventRepository.save(
                    Event.builder()
                            .title("The ObX and friends")
                            .tags(Set.of("hip hop", "inside", "concert"))
                            .venue(tor22)
                            .eventStart(LocalDateTime.parse("2021-07-17T19:00"))
                            .eventEnd(LocalDateTime.parse("2021-07-18T02:30").plusHours(24))
                            .date(LocalDateTime.parse("2021-07-17T19:00").toLocalDate())
                            .info("hämmernde Beats, Herzen und Sargnägel. Warum auch immer. Abriss seit '96: Dafür stehen die Gesellen aus der Vorstadt. Keine Preise gewinnen, dafür auch mal heiser singen.")
                            .artists(Set.of("The ObX", "DJ Jeezy", "Maxxx el toro"))
                            .price(12)
                            .build()
            );

            Event tor22_2 = eventRepository.save(
                    Event.builder()
                            .title("Heartbreak Festival")
                            .tags(Set.of("hip hop", "outside", "performance", "dj", "concert", "jam"))
                            .venue(tor22)
                            .eventStart(LocalDateTime.parse("2021-07-24T18:00"))
                            .eventEnd(LocalDateTime.parse("2021-07-26T02:30").plusHours(24))
                            .date(LocalDateTime.parse("2021-07-24T18:00").toLocalDate())
                            .info("Euch erwartet ein wundervolles Wochenende mit heftigen deutschlandweit bekannten DJ’s und Live Acts, vielen wunderschönen Bühnen, einem krachenden Abschlussfeuerwerk und zauberhaften Menschen.")
                            .artists(Set.of("Jack und der Hühne", "Waldemar ohne F", "Krishna Meier", "Stangenlob Dreiviertel"))
                            .price(25)
                            .build()
            );

            Event tor22_3 = eventRepository.save(
                    Event.builder()
                            .title("JJ Colemans wild days")
                            .tags(Set.of("hip hop", "concert", "jam", "outside", "political"))
                            .venue(grueneSchatztruhe)
                            .eventStart(LocalDateTime.parse("2021-07-29T20:00"))
                            .eventEnd(LocalDateTime.parse("2021-07-29T23:30").plusHours(24))
                            .date(LocalDateTime.parse("2021-07-29T20:00").toLocalDate())
                            .info("Gut zwei Jahre sind seit dem Debütalbum des Hamburgers ins Land gegangen. Was der Bonvivant in der Zwischenzeit gemacht hat? Natürlich hat er das Leben genossen – und wer JJ Coleman kennt, weiß, dass er das in vollen Zügen getan hat: Nachtclubs, Rauschmittel, schöne Frauen, gutes Essen. Klar, dass der 28-jährige diesen lockeren Lifestyle auf seinem neuen Album »Blueberry Boyz« noch und nöcher zelebriert. ")
                            .artists(Set.of("JJ Coleman"))
                            .price(5)
                            .build()
            );

            Event pflanzenmarkt = eventRepository.save(
                    Event.builder()
                            .title("Pflanzentauschbörse")
                            .tags(Set.of("outside"))
                            .venue(grueneSchatztruhe)
                            .eventStart(LocalDateTime.parse("2021-07-18T13:00"))
                            .eventEnd(LocalDateTime.parse("2021-07-18T18:30").plusHours(24))
                            .date(LocalDateTime.parse("2021-07-18T13:00").toLocalDate())
                            .info("Unsere tollen Mitarbeiter haben bei gegenseitigen Besuchen festgestellt, dass jeder so seine Pflanzen Schätzchen zu Hause hat. Wir haben fleißig Ableger seit dem Winter wachsen lassen und möchten nun wild tauschen. Wer da auch Spaß dran hat, kann gerne vorbei kommen und mitmachen.")
                            .artists(Set.of(""))
                            .price(0)
                            .build()
            );

            Event pflanzentalk = eventRepository.save(
                    Event.builder()
                            .title("Lets talk about plants baby")
                            .tags(Set.of("conversation", "exhibition", "outside"))
                            .venue(grueneSchatztruhe)
                            .eventStart(LocalDateTime.parse("2021-07-20T17:00"))
                            .eventEnd(LocalDateTime.parse("2021-07-20T20:30").plusHours(24))
                            .date(LocalDateTime.parse("2021-07-20T17:00").toLocalDate())
                            .info("Wollt ihr über Pflanzen sprechen? Wer da auch Spaß dran hat, kann gerne vorbei kommen und mitmachen.")
                            .artists(Set.of(""))
                            .price(0)
            );

            Event kreativTagebuch = eventRepository.save(
                Event.builder()
                        .title("Workshop Kreativtagebuch")
                        .tags(Set.of("conversation", "exhibition", "inside"))
                        .venue(grueneSchatztruhe)
                        .eventStart(LocalDateTime.parse("2021-07-17T11:00"))
                        .eventEnd(LocalDateTime.parse("2021-07-17T12:30").plusHours(24))
                        .date(LocalDateTime.parse("2021-07-17T11:00").toLocalDate())
                        .info("Wir bieten ab diesem Samstag einen mehrteiligen Workshop Kreativtagebuch im Wedding an. Weil wir das ganze Konzept zunächst auch testen möchten, ist dieser erste Durchlauf kostenlos, nur geringe Materialkosten werden fällig, sonst nichts. Wir erkunden an vier Nachmittagen verschiedene Vorgehensweisen und Techniken des Journaling, lernen auch verschiedene Herangehensweisen kennen und mögliche Ziele, warum man das vielleicht so macht. ")
                        .artists(Set.of("Elsa Wenger"))
                        .price(0)
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
                            .events(Set.of(hochbeet01, eventTrudeRuth02, eventTrudeRuth04, eventTrudeRuth03, tor22_3, tor22_2))
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
                            .events(Set.of(hochbeet01, eventTrudeRuth02, pflanzenmarkt, eventTrudeRuth04, eventTrudeRuth03, eventMenschMeier01, eventMenschMeier02))
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

            log.info("Preloading " + memberRepository.save(
                    Member.builder()
                            .username("kaiuwe")
                            .firstName("Kai Uwe")
                            .lastName("Salomon")
                            .password("kaimachtheutefrei")
                            .events(Set.of(hochbeet01, eventMenschMeier02, pflanzenmarkt, eventTrudeRuth04, tor22_3, tor22_1, tor22_2, eventTrudeRuth03))
                            .build())
            );

            log.info("Preloading " + memberRepository.save(
                    Member.builder()
                            .username("hagendersiebert")
                            .firstName("Hagen")
                            .lastName("Siebert")
                            .password("hagendermagen")
                            .events(Set.of(eventMenschMeier02, tor22_3, tor22_1, eventMenschMeier01, pflanzenmarkt))
                            .build())
            );

            log.info("Preloading " + memberRepository.save(
                Member.builder()
                        .username("erkanndas")
                        .firstName("erkan")
                        .lastName("Özmir-Hagen")
                        .password("erkanndasrichtiggut")
                        .events(Set.of(hochbeet01, hochbeet02, tor22_2, tor22_1, eventMenschMeier01, pflanzentalk, eventTrudeRuth01, eventTrudeRuth04))
                        .build())
        );
        };
    }
}
