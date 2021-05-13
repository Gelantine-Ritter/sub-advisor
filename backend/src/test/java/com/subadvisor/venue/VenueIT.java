package com.subadvisor.venue;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.ResultMatcher.matchAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Matti Henning
 * <p>
 * This class uses @SpringBootTest wich includes also Persistance Layer
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class VenueIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static Venue venue;
    private static Venue venueEntity;

    @BeforeAll
    static void setUpData() {
        venue = new Venue("about_blank", "blank@test.com", "cooler club");
    }

@Disabled
    @Test
    @Order(1)
    void createOneVenue() throws Exception {

        String res = mockMvc
                .perform(
                        post("/venues/")
                                .contentType("application/json")
                                .content(objectMapper.writeValueAsString(venue)
                                )
                )
                .andExpect(
                        matchAll(
                                status().isOk(),
                                jsonPath("$.name").value(venue.getName()),
                                jsonPath("$.info").value(venue.getInfo()),
                                jsonPath("$.email").value(venue.getEmail())
                        )
                )
                .andReturn()
                .getResponse()
                .getContentAsString();

        // save response as new Venue to use id for further tests
        venueEntity = new ObjectMapper().readValue(res, Venue.class);

    }

    @Disabled
    @Test
    @Order(2)
    void getOneVenueById() throws Exception {

        mockMvc
                .perform(get("/venues/" + venueEntity.getId()))
                .andExpect(matchAll(
                        status().isOk(),
                        jsonPath("$.id").value(venueEntity.getId()),
                        jsonPath("$.name").value(venueEntity.getName()),
                        jsonPath("$.info").value(venueEntity.getInfo()),
                        jsonPath("$.email").value(venueEntity.getEmail())
                ));
    }

    @Disabled
    @Test
    @Order(3)
    void updateOneVenueById() throws Exception {

        venueEntity.setName("Bla");

        mockMvc
                .perform(put("/venues/" + venueEntity.getId())
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(venueEntity)
                        )
                )
                .andExpect(matchAll(
                        status().isOk(),
                        jsonPath("$.id").value(venueEntity.getId()),
                        jsonPath("$.name").value(venueEntity.getName()),
                        jsonPath("$.info").value(venueEntity.getInfo()),
                        jsonPath("$.email").value(venueEntity.getEmail())
                ));
    }

    @Disabled
    @Test
    @Order(4)
    void deleteOneVenueById() throws Exception {

        mockMvc.perform(delete("/venues/" + venueEntity.getId()))
                .andExpect(status().isOk());

        mockMvc.perform(get("/venues/" + venueEntity.getId()))
                .andExpect(status().is(404));

    }
}
