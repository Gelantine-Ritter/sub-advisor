package com.subadvisor.api.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.subadvisor.api.auth.dto.AuthenticationRequest;
import com.subadvisor.api.member.Member;
import com.subadvisor.api.member.MemberRepository;
import com.subadvisor.api.venue.Venue;
import com.subadvisor.api.venue.VenueRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcPrint;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc(print = MockMvcPrint.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AuthIT {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    VenueRepository venueRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    private static final String USER_NAME_VALID = "user_name_valid";
    private static final String VENUE_NAME_VALID = "venue_name-valid";
    private static final String USER_NAME_NON_VALID = "user_name_non_valid";
    private static final String VENUE_NAME_NON_VALID = "venue_name_non_valid";

    private static final String PASSWORD_VALID = "password_valid";
    private static final String PASSWORD_NON_VALID = "password_non_valid";

    private static String TOKEN;

    @BeforeAll
    void setUp(){

        memberRepository.save(
                Member.builder()
                        .username(USER_NAME_VALID)
                        .password(PASSWORD_VALID)
                        .enabled(true)
                        .build()
        );

        String venueName = "tennis-cafe";

        venueRepository.save(
                Venue.builder()
                        .name("lala")
                        .username(VENUE_NAME_VALID)
                        .password(PASSWORD_VALID)
                        .enabled(true)
                        .build()
        );

    }


    // TODO: Entity user will become member once
    @Test
    @Order(1)
    void testUserAuthentication() throws Exception {

        AuthenticationRequest authReq = AuthenticationRequest.builder()
                .username(USER_NAME_VALID)
                .password(PASSWORD_VALID)
                .build();

        MockHttpServletResponse response = mockMvc
                .perform(
                        post("/authenticate/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(authReq))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.jwt").exists())
                .andDo(print())
                .andExpect(jsonPath("$.userId").exists())
                .andReturn()
                .getResponse();

        TOKEN = objectMapper.readValue(response.getContentAsString(), TokenResponseDto.class).getJwt();
    }

    @Test
    @Order(2)
    void testUserAuthorization() throws Exception{
        assertUserIsAuthenticated(TOKEN);
    }

    @Test
    @Order(3)
    void testVenueAuthentication() throws Exception {


        AuthenticationRequest authReq = AuthenticationRequest.builder()
                .username(VENUE_NAME_VALID)
                .password(PASSWORD_VALID)
                .build();

        MockHttpServletResponse response = mockMvc
                .perform(
                        post("/authenticate/")
                                .contentType("application/json")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(authReq))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.jwt").exists())
                .andExpect(jsonPath("$.userId").exists())
                .andReturn()
                .getResponse();


        TOKEN = objectMapper.readValue(response.getContentAsString(), TokenResponseDto.class).getJwt();
    }

    @Test
    @Order(4)
    void testVenueAuthorization() throws Exception{
        assertUserIsAuthenticated(TOKEN);
    }

    @Test
    @Order(5)
    void testUserAuthenticationWithWrongCredentials() throws Exception {

        AuthenticationRequest authReq = AuthenticationRequest.builder()
                .username(USER_NAME_VALID)
                .password(PASSWORD_NON_VALID)
                .build();

        mockMvc
                .perform(
                        post("/authenticate/")
                                .contentType("application/json")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(authReq))
                )
                .andDo(print())
                .andExpect(status().is(404))
                .andExpect(jsonPath("$.jwt").doesNotExist());
    }

    @Test
    @Order(6)
    void testUserIsNotAuthorized() throws Exception{
        mockMvc
                .perform(get("/hello")
                )
                .andDo(print())
                .andExpect(
                        status().is(401)
                );
    }


    private void assertUserIsAuthenticated(String token) throws Exception {
        mockMvc
                .perform(get("/hello")
                        .header("authorization", "Bearer " + token)
                )
                .andExpect(
                        status().isOk()
                )
                .andExpect(
                        content().string(containsString("Hello"))
                );
    }
}
