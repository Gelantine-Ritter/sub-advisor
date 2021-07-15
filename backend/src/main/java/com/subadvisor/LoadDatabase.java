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
            
            Venue schoenTheater = venueRepository.save(Venue.builder()
                    .username("schoen-schoen-theater")
                    .password("ichgeheinstheater")
                    .name("Schöneberger Theater")
                    .email("schoeneberger-theater@info.de")
                    .info("Das Schöneberger Theater beherbergt zwei Bühnen: Das Kleine Haus mit ca. 200 Plätzen in einem Saal von 1820 mit gemischtem Repertoire, das Große Haus mit ca. 450 PlÄtzen (1910 von Peter Mayer eingerichtet). Das Schöneberger ist ein Ort für hautnahes Theater, neue Texte und aktuelle Themen. Im Jahr 2008 beschloss der Senat, auf der Linkeseite des Gebäudes ein Probebühnenzentrum anzubauen. Das I-foermige Gebäude besitzt vier Probebühnen übereinander, außerdem Werkstaetten, Garderoben- und Bueroraeume enthalten. ")
                    .mobile("0154-3289941")
                    .hours(
                            Map.of("monday", "closed",
                                    "tuesday", "19:00 - 23:00",
                                    "wednesday", "closed",
                                    "thursday", "19:00 - 23:00",
                                    "friday", "19:00 - 23:00",
                                    "saturday", "19:00 - 23:00",
                                    "sunday", "19:00 - 23:00"
                            )
                    )
                    .address(
                            Map.of(
                                    "street", "Barbarossaplatz",
                                    "number", "5",
                                    "city", "Berlin",
                                    "plz", "10781"
                            )
                    )
                    .website("https://schoen-theater.de")
                    .build()
            );

            Venue tankstelle = venueRepository.save(Venue.builder()
                    .username("super-diesel-98")
                    .password("98dieselsuper")
                    .name("Tankstelle")
                    .email("zur-tankstelle@google.de")
                    .info("Geht Ihnen die Energie aus? Müssen Sie Ihren Benzintank auffüllen? Hier sind Sie richtig: bei der Tankstelle können Sie mit dem kältesten Bier Berlins Ihre Batterien wieder aufladen. Dazu gibts vor allem gutem Sound, lautem Sound, Live-Sound mit regelmäßigen Konzerten von weniger bekannten Bands, anderen mehr und auch Überraschungskonzerten. Bis bald.")
                    .mobile("0143-8478805")
                    .hours(
                            Map.of("monday", "17:00 - 01:00",
                                    "tuesday", "closed",
                                    "wednesday", "closed",
                                    "thursday", "17:00 - 01:00",
                                    "friday", "17:00 - 01:00",
                                    "saturday", "17:00 - 01:00",
                                    "sunday", "17:00 - 01:00"
                            )
                    )
                    .address(
                            Map.of(
                                    "street", "Holzmarktstraße",
                                    "number", "12-14",
                                    "city", "Berlin",
                                    "plz", "10179"
                            )
                    )
                    .website("https://zur-tankstelle.de")
                    .build()
            );

            Venue hertz = venueRepository.save(Venue.builder()
                    .username("100megaHertz")
                    .password("bipbipbip")
                    .name("Hertz")
                    .email("hertz-attack@info.de")
                    .info("this place stands for: No Racism / No Homophobia / No Sexism / No Transphobia / No Heterosexism. There will be no tolerance for unjust behaviour. Please respect the property of the event. Disclaimer as part of Covid-19 precautions: The venue offers a test center at the entrance. We must note, this is an ever changing situation, we will keep you informed with updates as to the protocols to follow. In order to acquire a safe and enjoyable experience we have to abide to certain precautions. In accordance with the requirements of the SARS-CoV-2 infection protection ordinance, documentation of presence must be collected. The current rules read: Negative test result / proof of recovery from vaccination. Arrive wearing your mask, covering your mouth and nose constantly. Wear it at all times when not seated. Ensure that a safe distance of 1.5 m between you and other persons is maintained, also in front of the bar or while standing in the toilette row.")
                    .mobile("0161-3297956")
                    .hours(
                            Map.of("monday", "closed",
                                    "tuesday", "closed",
                                    "wednesday", "closed",
                                    "thursday", "23:59 - 02:00",
                                    "friday", "23:59 - 10:00",
                                    "saturday", "23:59 - 10:00",
                                    "sunday", "23:59 - 10:00"
                            )
                    )
                    .address(
                            Map.of(
                                    "street", "Ziegrastraße",
                                    "number", "13",
                                    "city", "Berlin",
                                    "plz", "12057"
                            )
                    )
                    .website("https://hertz-attack.de")
                    .build()
            );

            // Load Venues
            log.info("Preloading " + trudeRuth);
            log.info("Preloading " + hochbeetGarten);
            log.info("Preloading " + schoenTheater);
            log.info("Preloading " + tankstelle);
            log.info("Preloading " + hertz);

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
                            .venue(tor22)
                            .eventStart(LocalDateTime.parse("2021-07-29T20:00"))
                            .eventEnd(LocalDateTime.parse("2021-07-29T23:30").plusHours(24))
                            .date(LocalDateTime.parse("2021-07-29T20:00").toLocalDate())
                            .info("Gut zwei Jahre sind seit dem Debütalbum des Hamburgers ins Land gegangen. Was der Bonvivant in der Zwischenzeit gemacht hat? Natürlich hat er das Leben genossen – und wer JJ Coleman kennt, weiß, dass er das in vollen Zügen getan hat: Nachtclubs, Rauschmittel, schöne Frauen, gutes Essen. Klar, dass der 28-jährige diesen lockeren Lifestyle auf seinem neuen Album »Blueberry Boyz« noch und nöcher zelebriert. ")
                            .artists(Set.of("JJ Coleman"))
                            .price(5)
                            .build()
            );

            //////

            Event schoenTheater01 = eventRepository.save(
                    Event.builder()
                            .title("Die kahle Sängerin")
                            .tags(Set.of("performance", "contemporary", "new wave", "inside"))
                            .venue(schoenTheater)
                            .eventStart(LocalDateTime.parse("2021-07-16T20:30"))
                            .eventEnd(LocalDateTime.parse("2021-07-16T23:00").plusHours(24))
                            .date(LocalDateTime.parse("2021-07-16T20:30").toLocalDate())
                            .info("Die kahle Sängerin ist ein Theaterstück von Eugène Ionesco. Die Uraufführung fand am 11. Mai 1950 im Théâtre des Noctambules in Paris unter der Regie von Nicolas Bataille statt. Seit 1957 steht es täglich auf dem Spielplan des Théâtre de la Huchette, das Die kahle Sängerin zusammen mit Die Unterrichtsstunde bis heute mehr als 17.000 Mal gezeigt und für seine Doppelvorstellung den Molière Theaterpreis erhalten hat. In seinen verschiedenen Interpretationen gehört das Anti-Stück, so sein Untertitel, in Frankreich damit zu den am häufigsten aufgeführten Beispielen des Absurden Theaters. ")
                            .artists(Set.of("Mariella Dietrich", "Günter Kinski", "Didier Depardieu", "Anna Waltz"))
                            .price(35)
                            .build()
            );

            Event tankstelle01 = eventRepository.save(
                    Event.builder()
                            .title("Rauchmilch")
                            .tags(Set.of("concert", "inside", "rock", "punk", "political"))
                            .venue(tankstelle)
                            .eventStart(LocalDateTime.parse("2021-07-17T21:30"))
                            .eventEnd(LocalDateTime.parse("2021-07-17T23:00").plusHours(24))
                            .date(LocalDateTime.parse("2021-07-17T21:30").toLocalDate())
                            .info("Nach eineinhalb Jahren ohne Konzert kehrt zu unserem größten Vergnügen Rauchmilch in die Tankstelle zurück. Das vierköpfige Team aus Berlin, Tokio und Edinburgh kombiniert Soundqualität, Inszenierung und Beteiligung des Publikums für Konzerte, die nah an Performances sind. Sie zu verpassen wäre ein Fehler...")
                            .artists(Set.of("Rauchmilch"))
                            .price(5)
                            .build()
            );

            Event hertz01 = eventRepository.save(
                    Event.builder()
                            .title("Trance X #11")
                            .tags(Set.of("outside", "dj", "techno", "acid"))
                            .venue(hertz)
                            .eventStart(LocalDateTime.parse("2021-07-17T23:59"))
                            .eventEnd(LocalDateTime.parse("2021-08-18T10:00").plusHours(24))
                            .date(LocalDateTime.parse("2021-07-17T23:59").toLocalDate())
                            .info("Just bring your sexy mask and enjoy. We'll make a test if you couldn't do it before ;)")
                            .artists(Set.of("DJ Trancemission, Hot Mama, Ily, DJ Spacer"))
                            .price(12)
                            .build()
            );

            // Load Venues
            log.info("Preloading " + eventTrudeRuth01);

            log.info("Preloading " + eventTrudeRuth02);

            log.info("Preloading " + eventTrudeRuth03);

            log.info("Preloading " + hochbeet01);

            log.info("Preloading " + hochbeet02);

            log.info("Preloading " + schoenTheater01);
            
            log.info("Preloading " + tankstelle01);

            log.info("Preloading " + hertz01);

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

            log.info("Preloading " + memberRepository.save(
                    Member.builder()
                            .username("kaiuwe")
                            .firstName("Kai Uwe")
                            .lastName("Salomon")
                            .password("kaimachtheutefrei")
                            .events(Set.of(hochbeet01, eventMenschMeier02, eventTrudeRuth04, tor22_3, tor22_1, tor22_2, eventTrudeRuth03))
                            .build())
            );

            log.info("Preloading " + memberRepository.save(
                    Member.builder()
                            .username("hagendersiebert")
                            .firstName("Hagen")
                            .lastName("Siebert")
                            .password("hagendermagen")
                            .events(Set.of(eventMenschMeier02, tor22_3, tor22_1, eventMenschMeier01))
                            .build())
            );

            log.info("Preloading " + memberRepository.save(
                Member.builder()
                        .username("erkanndas")
                        .firstName("erkan")
                        .lastName("Özmir-Hagen")
                        .password("erkanndasrichtiggut")
                        .events(Set.of(hochbeet01, hochbeet02, tor22_2, tor22_1, eventMenschMeier01, eventTrudeRuth01, eventTrudeRuth04))
                        .build())
        );
        };
    }
}
