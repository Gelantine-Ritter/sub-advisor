package com.subadvisor.api.auth;

import com.subadvisor.api.Driver;
import com.subadvisor.api.venue.Venue;
import com.subadvisor.operators.LoginOperator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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
public class RegistrationIT extends Driver {

    private static Venue VENUE;

    private static final String USER_NAME_VENUE = "my-party";
    private static final String NAME_VENUE = "://without blank";
    private static final String EMAIL_VENUE = "without@blank.li";
    private static final String PASSWORD_VENUE = "roterosen161";

    private static String TOKEN_VENUE;

    long ID_VENUE;

    private Driver DRIVER = driver();

    @BeforeAll
    void setUp() throws Exception {

        VENUE = Venue.builder()
                .username(USER_NAME_VENUE)
                .name(NAME_VENUE)
                .email(EMAIL_VENUE)
                .password(PASSWORD_VENUE)
                .build();
    }

    @Test
    @Order(1)
    void guestCanRegistrateAsAVenue() throws Exception {

        DRIVER.mockMvc()
                .perform(
                        post("/authenticate/registrate/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(
                                        VENUE
                                ))
                )
                .andDo(print())
                .andExpect(
                        matchAll(
                                status().isOk(),
                                jsonPath("$.username").value(VENUE.username()),
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

        // Store ID of the Venue to use in later tests
        ID_VENUE = venueRepository.findByName(NAME_VENUE).id();
    }

    @Test
    @Order(2)
    void logInNewRegistratedVenue() throws Exception {

        TOKEN_VENUE =
                new LoginOperator(DRIVER)
                        .login(USER_NAME_VENUE, PASSWORD_VENUE)
                        .token();
    }

    @Test
    @Order(3)
    void venueIsPresentInDataBase() throws Exception{

        DRIVER.mockMvc()
                .perform(
                        get("/venues/" + ID_VENUE)
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
    @Order(4)
    void venueCanGetPrivateProfileWhenLoggedIn() throws Exception{

        DRIVER.mockMvc()
                .perform(
                        get("/venues/" + ID_VENUE)
                                .header("authorization", "Bearer " + TOKEN_VENUE)
                )
                .andExpect(
                        matchAll(
                                status().isOk(),
                                jsonPath("$.username").value(VENUE.username()),
                                jsonPath("$.id").value(ID_VENUE),
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
}