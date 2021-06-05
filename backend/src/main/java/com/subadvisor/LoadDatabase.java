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

@Log4j2
@Configuration
class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(VenueRepository venueRepository, MemberRepository memberRepository, EventRepository eventRepository) {
        return args -> {

            // Load Venues
            log.info("Preloading " + venueRepository.save(
                    Venue.builder()
                            .username("trude-ruth")
                            .password("rudiruth")
                            .email("ruth@info.com")
                            .info("nette tanzabende")
                            .name("Trude Ruth")
                            .build()
            ));

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
