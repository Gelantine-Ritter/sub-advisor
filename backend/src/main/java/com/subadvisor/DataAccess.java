package com.subadvisor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.subadvisor.api.event.EventRepository;
import com.subadvisor.api.member.MemberRepository;
import com.subadvisor.api.venue.VenueRepository;
import com.subadvisor.security.CreatorCheck;
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
        protected VenueRepository venues;

        @Autowired
        protected MemberRepository members;

        @Autowired
        protected EventRepository events;

        @Autowired
        protected CreatorCheck check;

        protected DataAccess driver() {
            return this;
        }
}
