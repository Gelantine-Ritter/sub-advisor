package com.subadvisor.api.venue;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.subadvisor.api.Driver;
import com.subadvisor.operators.LoginOperator;
import io.jsonwebtoken.lang.Assert;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

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
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class VenueIT extends Driver {

    private static Venue VENUE;
    private static Venue OTHER_VENUE;
    private static Venue VENUE_UPDATED;

    private static final String USER_NAME_VENUE = "about-party";
    private static final String PASSWORD_VENUE = "roterosen161";
    private static final String NAME_VENUE = "://about blank";
    private static final String INFO_VENUE = "nette location für lange wochenenden";
    private static final String EMAIL_VENUE = "about@blank.li";

    private static final String USER_NAME_OTHER_VENUE = "tennis-café";
    private static final String PASSWORD_OTHER_VENUE = "guter-aufschlag";
    private static final String NAME_OTHER_VENUE = "cafè tennis";
    private static final String INFO_OTHER_VENUE = "gemütliche Kneipe mit schönem Keller";
    private static final String EMAIL_OTHER_VENUE = "tennis@schlaeger.info";

    private static String TOKEN_VENUE;
    private static String TOKEN_OTHER_VENUE;

    private Driver DRIVER = driver();

    @BeforeAll
    void setUp() throws Exception {

        // FIXME: Since registration is not implemtneted yet, this is roundabout
        VENUE = venueRepository.save(
                Venue.builder()
                        .username(USER_NAME_VENUE)
                        .password(PASSWORD_VENUE)
                        .name(NAME_VENUE)
                        .email(EMAIL_VENUE)
                        .info(INFO_VENUE)
                        .build()
        );

        OTHER_VENUE = venueRepository.save(
                Venue.builder()
                        .username(USER_NAME_OTHER_VENUE)
                        .password(PASSWORD_OTHER_VENUE)
                        .name(NAME_OTHER_VENUE)
                        .email(EMAIL_OTHER_VENUE)
                        .info(INFO_OTHER_VENUE)
                        .build()
        );

        VENUE_UPDATED = Venue.builder()
                .username(USER_NAME_VENUE)
                .password(PASSWORD_VENUE)
                .name(NAME_VENUE + "_update")
                .email(EMAIL_VENUE + "_update")
                .info(INFO_VENUE + "_update")
                .build();

        // FIXME: Not optimal. for now it is not possible that all dependencies are autowired once in abstract class.
        TOKEN_VENUE =
                new LoginOperator(DRIVER)
                        .login(USER_NAME_VENUE, PASSWORD_VENUE)
                        .token();

        TOKEN_OTHER_VENUE =
                new LoginOperator(DRIVER)
                        .login(USER_NAME_OTHER_VENUE, PASSWORD_OTHER_VENUE)
                        .token();
    }

    @Test
    @Order(1)
    void guestCanReadPublicVenueAccount() throws Exception {

        DRIVER.mockMvc()
                .perform(
                        get("/venues/" + VENUE.id())
                )
                .andExpect(
                        matchAll(
                                status().isOk(),
                                jsonPath("$.username").doesNotExist(),
                                jsonPath("$.id").doesNotExist(),
                                jsonPath("$.name").value(VENUE.name()),
                                jsonPath("$.info").value(VENUE.info()),
                                jsonPath("$.email").value(VENUE.email()),
                                jsonPath("$.password").doesNotExist(),
                                jsonPath("$.created").doesNotExist(),
                                jsonPath("$.modifiedAt").doesNotExist(),
                                status().isOk()
                        )
                )
                .andDo(print())
                .andReturn()
                .getResponse();
    }

    @Test
    @Order(2)
    void venueCanReadOwnPrivateVenueAccount() throws Exception {

        DRIVER.mockMvc()
                .perform(
                        get("/venues/" + VENUE.id())
                                .header("authorization", "Bearer " + TOKEN_VENUE)
                )
                .andExpect(
                        matchAll(
                                status().isOk(),
                                jsonPath("$.username").value(VENUE.username()),
                                jsonPath("$.id").value(VENUE.id()),
                                jsonPath("$.name").value(VENUE.name()),
                                jsonPath("$.info").value(VENUE.info()),
                                jsonPath("$.email").value(VENUE.email()),
                                jsonPath("$.password").doesNotExist(),
                                jsonPath("$.created").exists(),
                                jsonPath("$.modifiedAt").exists(),
                                status().isOk()
                        )
                )
                .andDo(print())
                .andReturn()
                .getResponse();
    }


    @Test
    @Order(3)
    void venueCanReadOtherPublicVenueAccount() throws Exception {

        DRIVER.mockMvc()
                .perform(
                        get("/venues/" + OTHER_VENUE.id())
                                .header("authorization", "Bearer " + TOKEN_VENUE)
                )
                .andExpect(
                        matchAll(
                                status().isOk(),
                                jsonPath("$.username").doesNotExist(),
                                jsonPath("$.id").doesNotExist(),
                                jsonPath("$.name").value(OTHER_VENUE.name()),
                                jsonPath("$.info").value(OTHER_VENUE.info()),
                                jsonPath("$.email").value(OTHER_VENUE.email()),
                                jsonPath("$.password").doesNotExist(),
                                jsonPath("$.created").doesNotExist(),
                                jsonPath("$.modifiedAt").doesNotExist(),
                                status().isOk()
                        )
                )
                .andDo(print())
                .andReturn()
                .getResponse();
    }

    @Test
    @Order(4)
    void venueCanUpdateOwnVenueAccount() throws Exception {

        Long idhello = VENUE.id();

        DRIVER.mockMvc()
                .perform(
                        put("/venues/" + VENUE.id())
                                .header("authorization", "Bearer " + TOKEN_VENUE)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(
                                        VENUE_UPDATED
                                ))
                )
                .andExpect(
                        matchAll(
                                status().isOk(),
                                jsonPath("$.username").value(VENUE.username()),
                                jsonPath("$.id").value(VENUE.id()),
                                jsonPath("$.name").value(VENUE_UPDATED.name()),
                                jsonPath("$.info").value(VENUE_UPDATED.info()),
                                jsonPath("$.email").value(VENUE_UPDATED.email()),
                                jsonPath("$.password").doesNotExist(),
                                jsonPath("$.created").exists(),
                                jsonPath("$.modifiedAt").exists(),
                                status().isOk()
                        )
                )
                .andDo(print())
                .andReturn()
                .getResponse();


    }

    @Test
    @Order(5)
    void venueCanNotUpdateOtherVenueAccount() throws Exception {

        DRIVER.mockMvc()
                .perform(
                        put("/venues/" + OTHER_VENUE.id())
                                .header("authorization", "Bearer " + TOKEN_VENUE)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(
                                        VENUE_UPDATED
                                ))
                )
                .andDo(print())
                .andExpect(
                        status().is(403)
                );

        VENUE.id();

        Assertions.assertEquals(OTHER_VENUE.username(), venueRepository
                .findById(OTHER_VENUE.id())
                .get()
                .username());
    }

    @Test
    @Order(6)
    void venueCanNotDeleteOtherVenueAccount() throws Exception {

        DRIVER.mockMvc()
                .perform(
                        delete("/venues/" + OTHER_VENUE.id())
                                .header("authorization", "Bearer " + TOKEN_VENUE)
                )
                .andDo(print())
                .andExpect(
                        status().is(403)
                );

        VENUE.id();

        Assertions.assertNotNull(venueRepository.findById(OTHER_VENUE.id()));
    }

    @Test
    @Order(7)
    void venueCanDeleteOwnVenueAccount() throws Exception {

        DRIVER.mockMvc()
                .perform(
                        delete("/venues/" + VENUE.id())
                                .header("authorization", "Bearer " + TOKEN_VENUE)
                )
                .andDo(print())
                .andExpect(
                        status().is(200)
                );


        Assertions.assertEquals(Optional.empty(), venueRepository.findById(VENUE.id()));
    }

    @Test
    @Order(8)
    void guestCanNotReadDeleteVenueAccount() throws Exception {

        DRIVER.mockMvc()
                .perform(
                        get("/venues/" + VENUE.id())
                )
                .andExpect(
                        status().is(401)
                )
                .andDo(print())
                .andReturn()
                .getResponse();
    }
}