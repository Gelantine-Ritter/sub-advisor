package com.subadvisor;

import com.subadvisor.user.User;
import com.subadvisor.user.UserRepository;
import com.subadvisor.venue.Venue;
import com.subadvisor.venue.VenueRepository;
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
            log.info("Preloading " + venueRepository.save(new Venue("://about_blank", "blank@info.com", "netter Ort für lange Wochenenden")));
            log.info("Preloading " + venueRepository.save(new Venue("café tennis", "zumtennis@info.com", "nettes café für nette Abende")));

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
