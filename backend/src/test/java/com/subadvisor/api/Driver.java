package com.subadvisor.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.subadvisor.api.event.EventRepository;
import com.subadvisor.api.member.MemberRepository;
import com.subadvisor.api.venue.VenueRepository;

import lombok.Data;
import lombok.experimental.Accessors;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

/**
 * @author Matti Henning
 *
 * The Driver class holds all relevant Beans for Integration-testing
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@Accessors(fluent = true, chain = true)
@Data
public abstract class Driver {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected VenueRepository venueRepository;

    @Autowired
    protected MemberRepository memberRepository;

    @Autowired
    protected EventRepository eventRepository;

    protected Driver driver() {
        return this;
    }

}
