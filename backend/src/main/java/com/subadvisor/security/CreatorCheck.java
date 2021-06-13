package com.subadvisor.security;

import com.subadvisor.DataAccess;
import com.subadvisor.api.event.Event;
import com.subadvisor.api.venue.Venue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

import static java.lang.String.format;

@Component
public class CreatorCheck {

    @Autowired
    DataAccess data;

    public boolean checkIfOwner(String entityId, Authentication authentication) {
        Event event = data
                .events()
                .findById(Long.parseLong(entityId))
                .orElseThrow(() -> new EntityNotFoundException(
                        format("No Events found for %s ", entityId)
                ));

        return event
                .venue()
                .getId()
                .equals(
                        ((Venue) authentication.getPrincipal()).getId()
                );
    }
}
