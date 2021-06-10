package com.subadvisor.api.event;

import com.subadvisor.api.Driver;
import com.subadvisor.api.event.dto.EventCreateDto;
import com.subadvisor.api.event.dto.EventFalseDto;
import com.subadvisor.api.event.dto.EventUpdateDto;
import com.subadvisor.api.venue.Venue;
import com.subadvisor.operators.LoginOperator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.util.Optional;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.ResultMatcher.matchAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.ResultMatcher.matchAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.hamcrest.Matchers.*;

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
    private static Venue VENUE_OTHER;
    private static EventCreateDto EVENT_DTO;
    private static EventUpdateDto EVENT_UPDATE;
    private static EventFalseDto EVENT_DTO_FALSE;
    private static Event EVENT_CRALLE;
    private static Event EVENT_OTHER;

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

    private static String TOKEN_VENUE;
    private static String TOKEN_OTHER_VENUE;


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

        VENUE_OTHER = venueRepository.save(
                Venue.builder()
                        .username("Other")
                        .name("Other")
                        .password(PASSWORD_VENUE)
                        .build()
        );

        EVENT_OTHER = eventRepository.save(
                Event.builder()
                        .title("Other")
                        .venue(VENUE_OTHER)
                        .build()
        );

        EVENT_DTO = EventCreateDto.builder()
                .title(EVENT_TITLE)
                .venueId(VENUE_CRALLE.id().toString())
                .info(EVENT_INFO)
                .artists(EVENT_ARTISTS)
                .price(EVENT_PRICE)
                .eventStart(EVENT_START)
                .eventEnd(EVENT_END)
                .build();

        EVENT_DTO_FALSE = EventFalseDto.builder()
                .title(null)
                .venueId(VENUE_CRALLE.id().toString())
                .info(EVENT_INFO)
                .artists(EVENT_ARTISTS)
                .price(EVENT_PRICE)
                .eventStart(EVENT_START)
                .eventEnd(EVENT_END)
                .build();

        EVENT_UPDATE = EventUpdateDto.builder()
                .title(EVENT_TITLE)
                .info(EVENT_INFO)
                .artists(EVENT_ARTISTS)
                .price("15.00")
                .eventStart(EVENT_START)
                .eventEnd(EVENT_END)
                .build();

        TOKEN_VENUE = new LoginOperator(DRIVER)
                .login(USER_NAME_VENUE, PASSWORD_VENUE)
                .token();

        TOKEN_OTHER_VENUE = new LoginOperator(DRIVER)
                .login("Other", PASSWORD_VENUE)
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
    void venueCannotCreateEventWithoutRequiredProps() throws Exception {

        MockHttpServletResponse response = DRIVER.mockMvc()
                .perform(
                        post("/events/")
                                .header("authorization", "Bearer " + TOKEN_VENUE)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(
                                        EVENT_DTO_FALSE
                                ))
                )
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.errors", hasItem("Please provide a title")))
                .andExpect(jsonPath("$.errors", hasItem("title should not be empty")))
                .andReturn()
                .getResponse();

        EVENT_CRALLE = objectMapper.readValue(response.getContentAsString(), Event.class);

    }

    @Test
    @Order(4)
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
                                jsonPath("$.eventStart").value(EVENT_START),
                                jsonPath("$.eventEnd").value(EVENT_END),
                                status().isOk()
                        )
                )
                .andReturn()
                .getResponse();

        EVENT_CRALLE = objectMapper.readValue(response.getContentAsString(), Event.class);

    }

    @Test
    @Order(5)
    void guestCanGetExistingEvent() throws Exception {

        DRIVER.mockMvc()
                .perform(
                        get("/events/" + EVENT_CRALLE.id())
                )
                .andDo(print())
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
                                jsonPath("$.eventStart").value(EVENT_START),
                                jsonPath("$.eventEnd").value(EVENT_END)
                        )
                )
                .andReturn()
                .getResponse();
    }
    @Test
    @Order(6)
    void guestCanGetEventsOfVenue() throws Exception {

        DRIVER.mockMvc()
                .perform(
                        get("/events/").param("venue", VENUE_CRALLE.id().toString())
                )
                .andExpect(
                        matchAll(
                                matchAll(
                                        jsonPath("$").isArray(),
                                        jsonPath("$", hasSize(1)),
                                        jsonPath("$[0].title", is(EVENT_CRALLE.title()))
                                )
                        )
                )
                .andDo(print())
                .andReturn()
                .getResponse();

        DRIVER.mockMvc()
                .perform(
                        get("/events/").param("venue", VENUE_OTHER.id().toString())
                )
                .andExpect(
                        matchAll(
                                matchAll(
                                        jsonPath("$").isArray(),
                                        jsonPath("$", hasSize(1)),
                                        jsonPath("$[0].title", is(EVENT_OTHER.title()))
                                )
                        )
                )
                .andDo(print())
                .andReturn()
                .getResponse();
    }

    @Test
    @Order(7)
    void guestCanNotGetEventsOfNonExistingVenue() throws Exception {

        DRIVER.mockMvc()
                .perform(
                        get("/events/").param("venue", "32489567")
                )
                .andDo(print())
                .andReturn()
                .getResponse();
    }

    @Test
    @Order(8)
    void guestCanGetAllEvents() throws Exception {

        DRIVER.mockMvc()
                .perform(
                        get("/events/")
                )
                .andExpect(
                        matchAll(
                                status().isOk(),
                                jsonPath("$").isArray(),
                                jsonPath("$", hasSize(3)),
                                jsonPath("$[1].title", is(EVENT_OTHER.title())),
                                jsonPath("$[2].title", is(EVENT_CRALLE.title()))

                        )
                )
                .andReturn()
                .getResponse();
    }

    @Test
    @Order(9)
    void venueCanUpdateOwnEvent() throws Exception {

        DRIVER.mockMvc()
                .perform(
                        put("/events/" + EVENT_CRALLE.id())
                                .header("authorization", "Bearer " + TOKEN_VENUE)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(
                                        EVENT_UPDATE
                                ))
                )
                .andExpect(
                        matchAll(
                                jsonPath("$.id").exists(),
                                jsonPath("$.title").value(EVENT_TITLE),
                                jsonPath("$.info").value(EVENT_INFO),
                                jsonPath("$.artists").exists(),
                                jsonPath("$.price").value(Double.parseDouble(EVENT_UPDATE.price())),
                                jsonPath("$.created").exists(),
                                jsonPath("$.modifiedAt").exists(),
                                jsonPath("$.eventStart").value(EVENT_START),
                                jsonPath("$.eventEnd").value(EVENT_END),
                                status().isOk()
                        )
                )
                .andReturn()
                .getResponse();
    }

    @Test
    @Order(10)
    void guestCanNotUpdateVenuesEvent() throws Exception {

        DRIVER.mockMvc()
                .perform(
                        put("/events/" + EVENT_CRALLE.id())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(
                                        EVENT_UPDATE
                                ))
                )
                .andExpect(
                        status().is4xxClientError()
                )
                .andReturn()
                .getResponse();
    }

    @Test
    @Order(11)
    void otherVenueCanNotUpdateVenuesEvent() throws Exception {

        DRIVER.mockMvc()
                .perform(
                        put("/events/" + EVENT_CRALLE.id())
                                .header("authorization", "Bearer " + TOKEN_OTHER_VENUE)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(
                                        EVENT_UPDATE
                                ))
                )
                .andDo(print())
                .andExpect(
                        status().is4xxClientError()
                )
                .andReturn()
                .getResponse();
    }

    @Test
    @Order(12)
    void otherVenueCanNotDeleteVenuesEvent() throws Exception {

        DRIVER.mockMvc()
                .perform(
                        delete("/events/" + EVENT_CRALLE.id())
                                .header("authorization", "Bearer " + TOKEN_OTHER_VENUE)
                )
                .andExpect(
                        status().is4xxClientError()
                )
                .andReturn()
                .getResponse();

        Assertions.assertNotNull(eventRepository.findById(EVENT_CRALLE.id()));
    }

    @Test
    @Order(12)
    void VenueCanDeleteOwnEvent() throws Exception {

        DRIVER.mockMvc()
                .perform(
                        delete("/events/" + EVENT_CRALLE.id())
                                .header("authorization", "Bearer " + TOKEN_VENUE)
                )
                .andDo(print())
                .andReturn()
                .getResponse();

        Assertions.assertEquals(Optional.empty(), eventRepository.findById(EVENT_CRALLE.id()));
    }
}
