package com.subadvisor.api.venue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class VenueTest {


    private static Venue venue;

    @BeforeAll
    public static void setUp () {
        venue = Venue.builder()
                .username("bergi-gangster91")
                .password("geheim")
                .name("Berghain")
                .email("berghain@steil.de")
                .info("Tolle Hütte, viel Freude.")
                .build();
    }

    @Test
    public void testGetVenueName() {
        assertEquals(venue.name(), "Berghain");
    }

    @Test
    public void testGetVenueEmail() {
        assertEquals("berghain@steil.de", venue.email());
    }

    @Test
    public void testGetVenueInfo() {
        assertEquals(venue.info(), "Tolle Hütte, viel Freude.");
    }

    @Test
    public void testSetVenueName() {
        String name = "AboutBlank";
        venue.name(name);
        assertEquals(name, venue.name());
    }

    @Test
    public void testSetVenueEmail() {
        String email = "andereMail@berghain.com";
        venue.email(email);
        assertEquals(email, venue.email());
    }

    @Test
    public void testSetVenueInfo() {
        String info = "War mal hier, war nicht so...";
        venue.info(info);
        assertEquals(info, venue.info());
    }
}


