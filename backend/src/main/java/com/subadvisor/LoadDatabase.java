package com.subadvisor;

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
    CommandLineRunner initDatabase(VenueRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Venue("://about_blank", "blank@info.com", "netter Ort für lange Wochenenden")));
            log.info("Preloading " + repository.save(new Venue("café tennis", "zumtennis@info.com", "nettes café für nette Abende")));
        };
    }
}
