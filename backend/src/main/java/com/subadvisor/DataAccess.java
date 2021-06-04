package com.subadvisor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.subadvisor.api.event.EventRepository;
import com.subadvisor.api.member.MemberRepository;
import com.subadvisor.api.venue.VenueRepository;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Matti Henning
 *
 * The Driver class holds all relevant Beans for DataAccess
 */
@Accessors(fluent = true, chain = true)
@Data
public abstract class DataAccess {

        @Autowired
        protected ObjectMapper objectMapper;

        @Autowired
        protected VenueRepository venueRepository;

        @Autowired
        protected MemberRepository memberRepository;

        @Autowired
        protected EventRepository eventRepository;

        protected DataAccess driver() {
            return this;
        }
}
