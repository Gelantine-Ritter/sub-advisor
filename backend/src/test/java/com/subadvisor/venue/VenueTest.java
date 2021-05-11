package com.subadvisor.venue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class VenueTest {


    private static Venue venue;

    @BeforeAll
    public static void setUp () {
        venue = new Venue("Berghain", "berghain@steil.de", "Tolle Hütte, viel Freude.");
    }

    @Test
    public void testGetVenueName() {
        assertEquals(venue.getName(), "Berghain");
    }

    @Test
    public void testGetVenueEmail() {
        assertEquals("berghain@steil.de", venue.getEmail());
    }

    @Test
    public void testGetVenueInfo() {
        assertEquals(venue.getInfo(), "Tolle Hütte, viel Freude.");
    }

    @Test
    public void testSetVenueName() {
        String name = "AboutBlank";
        venue.setName(name);
        assertEquals(name, venue.getName());
    }

    @Test
    public void testSetVenueEmail() {
        String email = "andereMail@berghain.com";
        venue.setEmail(email);
        assertEquals(email, venue.getEmail());
    }

    @Test
    public void testSetVenueInfo() {
        String info = "War mal hier, war nicht so...";
        venue.setInfo(info);
        assertEquals(info, venue.getInfo());
    }
}


