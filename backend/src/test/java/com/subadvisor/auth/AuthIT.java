package com.subadvisor.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.subadvisor.user.User;
import com.subadvisor.user.UserRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.ResultMatcher.matchAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AuthIT {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    private static final String USER_NAME_VALID = "Testi Testimon";
    private static final String PASSWORD_VALID = "test";
    private static String TOKEN;


    @Test
    @Order(1)
    void authenticateTestUser_AndRestrieveTokenFromHeader() throws Exception {

        User user = userRepository.save(
                User.builder()
                        .username(USER_NAME_VALID)
                        .password(PASSWORD_VALID)
                        .enabled(true)
                        .build()
        );

        AuthenticationRequest authReq = AuthenticationRequest.builder()
                .username(USER_NAME_VALID)
                .password(PASSWORD_VALID)
                .build();

        MockHttpServletResponse response = mockMvc
                .perform(
                        post("/authenticate")
                                .contentType("application/json")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(authReq))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.jwt").exists())
                .andReturn()
                .getResponse();

        TOKEN = objectMapper.readValue(response.getContentAsString(), TokenResponseDTO.class).getJwt();
    }

    @Test
    @Order(2)
    void useTokenInHeaderForAuthorization() throws Exception {

        mockMvc.perform(
                get("/hello")

                        .header("authorization", "Bearer " + TOKEN)
        ).andExpect(
                status().isOk()
        ).andExpect(
                content().string(containsString("Hello"))
        );
    }

}
