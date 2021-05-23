package com.subadvisor.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.subadvisor.user.User;
import com.subadvisor.user.UserRepository;
import com.subadvisor.user.UserRole;
import com.subadvisor.venue.Venue;
import com.subadvisor.venue.VenueRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RegistrationIT {

    @Autowired
    UserRepository userRepository;

    @Autowired
    VenueRepository venueRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    private static final String USER_NAME_VALID = "user_name_valid";
    private static final String VENUE_NAME_VALID = "venue_name-valid";
    private static final String USER_EMAIL_VALID = "user_email_valid";
    private static final String USER_NAME_NON_VALID = "user_name_non_valid";
    private static final String VENUE_NAME_NON_VALID = "venue_name_non_valid";
    private static final String USER_EMAIL_NON_VALID = "user_email_non_valid";

    private static final String PASSWORD_VALID = "password_valid";
    private static final String PASSWORD_NON_VALID = "password_non_valid";

    User user;

    @BeforeAll
    void setUp(){

    }

    @Test
    void testUserRegistration () throws Exception {
        user = User.builder()
                .username(USER_NAME_VALID)
                .password(PASSWORD_VALID)
                .userRole(UserRole.MEMBER)
                .build();

        MockHttpServletResponse response = mockMvc
                .perform(
                        post("/authenticate/registration")
                                .contentType("application/json")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(user))
                )
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();
        System.out.println(response.getContentAsString());
    }

    @Test
    void testVenueRegistration () throws Exception {

    }


}
