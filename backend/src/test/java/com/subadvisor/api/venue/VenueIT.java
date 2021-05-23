package com.subadvisor.api.venue;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.ResultMatcher.matchAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

/**
 * @author Matti Henning
 * <p>
 * This class uses @SpringBootTest wich includes also Persistance Layer
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc( addFilters = false)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class VenueIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static Venue venue;
    private static Venue venueEntity;

    private static final String USER_NAME = "about-party";
    private static final String PASSWORD = "roterosen161";
    private static final String NAME = "://about blank";
    private static final String INFO = "nette location für lange wochenenden";
    private static final String EMAIL = "about@blank.li";


    @BeforeAll
    static void setUpData() {

        venue = Venue.builder()
                .username(USER_NAME)
                .password(PASSWORD)
                .name(NAME)
                .email(EMAIL)
                .info(INFO)
                .build();
    }

    @Test
    @Order(1)
    void createOneVenue() throws Exception {

        String res = mockMvc
                .perform(
                        post("/venues/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(venue)
                                )
                )
                .andDo(print())
                .andExpect(
                        matchAll(
                                status().isOk(),
                                jsonPath("$.username").value(venue.username()),
                                jsonPath("$.name").value(venue.name()),
                                jsonPath("$.info").value(venue.info()),
                                jsonPath("$.email").value(venue.email()),
                                jsonPath("$.password").doesNotExist()
                        )
                )
                .andReturn()
                .getResponse()
                .getContentAsString();

        // save response as new Venue to use id for further tests
        venueEntity = new ObjectMapper().readValue(res, Venue.class);

    }

    @Test
    @Order(2)
    void getOneVenueById() throws Exception {

        mockMvc
                .perform(get("/venues/" + venueEntity.id()))
                .andExpect(matchAll(
                        status().isOk(),
                        jsonPath("$.id").value(venueEntity.id()),
                        jsonPath("$.name").value(venueEntity.name()),
                        jsonPath("$.info").value(venueEntity.info()),
                        jsonPath("$.email").value(venueEntity.email())
                ));
    }

    @Test
    @Order(3)
    void updateOneVenueById() throws Exception {

        venueEntity.name("Bla");

        mockMvc
                .perform(put("/venues/" + venueEntity.id())
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(venueEntity)
                        )
                )
                .andExpect(matchAll(
                        status().isOk(),
                        jsonPath("$.id").value(venueEntity.id()),
                        jsonPath("$.name").value(venueEntity.name()),
                        jsonPath("$.info").value(venueEntity.info()),
                        jsonPath("$.email").value(venueEntity.email())
                ));
    }

    @Test
    @Order(4)
    void deleteOneVenueById() throws Exception {

        mockMvc.perform(delete("/venues/" + venueEntity.id()))
                .andExpect(status().isOk());

        mockMvc.perform(get("/venues/" + venueEntity.id()))
                .andExpect(status().is(404));

    }
}
