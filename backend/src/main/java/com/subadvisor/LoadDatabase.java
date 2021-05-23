package com.subadvisor;

import com.subadvisor.api.user.User;
import com.subadvisor.api.user.UserRepository;
import com.subadvisor.api.venue.Venue;
import com.subadvisor.api.venue.VenueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(VenueRepository venueRepository, UserRepository userRepository) {
        return args -> {

            // Load Venues
            log.info("Preloading " + venueRepository.save(
                    Venue.builder()
                            .username("about-blank")
                            .password("blank blank")
                            .email("blank@info.com")
                            .info("netter Ort für lange Wochenenden")
                            .name("://about_blank")
                            .build()
            ));


            /*
            log.info("Preloading " + venueRepository.save(new Venue("café tennis", "zumtennis@info.com", "nettes café für nette Abende")));
*/
            // Load TestUser
            log.info("Preloading " + userRepository.save(
                    User.builder()
                            .username("Jimmy-Bleu")
                            .password("supercool")
                            .enabled(true)

                            .build())
            );

            log.info("Preloading " + userRepository.save(
                    User.builder()
                            .username("Nayla Thalhammer")
                            .password("hammerschlag")
                            .build())
            );
        };
    }
}
