package com.subadvisor.api.event;

import com.subadvisor.api.Driver;
import com.subadvisor.api.event.dto.EventCreateDto;
import com.subadvisor.api.venue.Venue;
import com.subadvisor.api.event.Event;
import com.subadvisor.operators.LoginOperator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.springframework.test.web.servlet.ResultMatcher.matchAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.ResultMatcher.matchAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

/**
 * @author Matti Henning
 * <p>
 * Integrationtests for Event-Endpoint
 */
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EventIT extends Driver {

    private static Venue VENUE_CRALLE;
    private static EventCreateDto EVENT_DTO;
    private static Event EVENT_CRALLE;

    private static final String USER_NAME_VENUE = "crallecralle";
    private static final String PASSWORD_VENUE = "CralleCralle";
    private static final String NAME_VENUE = "Cafè Cralle";
    private static final String INFO_VENUE = "nette bar im Wedding. Seit 40 Jahren.";
    private static final String EMAIL_VENUE = "cralle@cral.li";

    private static final String EVENT_TITLE = "Away From Everything";
    private static final String EVENT_INFO = "Real authentic female hardcore am Samstag";
    private static final String[] EVENT_ARTISTS = {"Krimewatch", "The Distillers", "Bikini Kill"};
    private static final String EVENT_PRICE = "10.00";
    private static final String EVENT_START = "2021-09-04T20:00:00";
    private static final String EVENT_END = "2021-09-04T23:00:00";
    //private static final LocalDateTime EVENT_START = LocalDateTime.of(2021, 9, 4, 20, 00);
    //private static final LocalDateTime EVENT_END = LocalDateTime.of(2021, 9, 4, 23, 00);

    private static String TOKEN_VENUE;


    private Driver DRIVER = driver();


    @BeforeAll
    void setup() throws Exception {

        // create Venue in Database

        VENUE_CRALLE = venueRepository.save(
                Venue.builder()
                        .username(USER_NAME_VENUE)
                        .password(PASSWORD_VENUE)
                        .name(NAME_VENUE)
                        .email(EMAIL_VENUE)
                        .info(INFO_VENUE)
                        .build()
        );

        EVENT_DTO = EventCreateDto.builder()
                .title(EVENT_TITLE)
                .venueId(VENUE_CRALLE.id().toString())
                .info(EVENT_INFO)
                .artists(EVENT_ARTISTS)
                .price(EVENT_PRICE)
                .start(EVENT_START)
                .end(EVENT_END)
                .build();

        TOKEN_VENUE = new LoginOperator(DRIVER)
                .login(USER_NAME_VENUE, PASSWORD_VENUE)
                .token();
    }

    @Test
    @Order(1)
    void guestCannotCreateAnEvent() throws Exception {
        DRIVER.mockMvc()
                .perform(
                        post("/events/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(
                                        EVENT_DTO
                                ))
                )
                .andDo(print())
                .andExpect(
                        matchAll(
                                status().is4xxClientError()
                        )
                )
                .andReturn()
                .getResponse();
    }

    @Test
    @Order(2)
    void guestCannotGetNonExistingEvent() throws Exception {

        DRIVER.mockMvc()
                .perform(
                        get("/events/567")
                )
                .andExpect(
                        matchAll(
                                status().is4xxClientError()
                        )
                )
                .andReturn()
                .getResponse();
    }

    @Test
    @Order(3)
    void venueCanCreateAnEvent() throws Exception {

        MockHttpServletResponse response = DRIVER.mockMvc()
                .perform(
                        post("/events/")
                                .header("authorization", "Bearer " + TOKEN_VENUE)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(
                                        EVENT_DTO
                                ))
                )
                .andDo(print())
                .andExpect(
                        matchAll(
                                jsonPath("$.id").exists(),
                                jsonPath("$.title").value(EVENT_TITLE),
                                jsonPath("$.info").value(EVENT_INFO),
                                jsonPath("$.artists").exists(),
                                jsonPath("$.price").value(Double.parseDouble(EVENT_PRICE)),
                                jsonPath("$.created").exists(),
                                jsonPath("$.modifiedAt").exists(),
                                jsonPath("$.start").value(EVENT_START),
                                jsonPath("$.end").value(EVENT_END),
                                status().isOk()
                        )
                )
                .andReturn()
                .getResponse();

        EVENT_CRALLE = objectMapper.readValue(response.getContentAsString(), Event.class);

    }

    @Test
    @Order(4)
    void guestCantGetExistingEvent() throws Exception {

        DRIVER.mockMvc()
                .perform(
                        get("/events/" + EVENT_CRALLE.id())
                )
                .andExpect(
                        matchAll(
                                status().isOk(),
                                jsonPath("$.id").exists(),
                                jsonPath("$.title").value(EVENT_TITLE),
                                jsonPath("$.info").value(EVENT_INFO),
                                jsonPath("$.artists").exists(),
                                jsonPath("$.price").value(Double.parseDouble(EVENT_PRICE)),
                                jsonPath("$.created").exists(),
                                jsonPath("$.modifiedAt").exists(),
                                jsonPath("$.start").value(EVENT_START),
                                jsonPath("$.end").value(EVENT_END)
                        )
                )
                .andReturn()
                .getResponse();
    }

}
