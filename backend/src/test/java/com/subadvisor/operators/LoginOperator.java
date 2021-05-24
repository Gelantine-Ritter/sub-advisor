package com.subadvisor.operators;


import com.subadvisor.api.Driver;
import com.subadvisor.api.auth.TokenResponseDto;
import com.subadvisor.api.auth.dto.AuthenticationRequest;

import lombok.AllArgsConstructor;

import lombok.Data;

import lombok.experimental.Accessors;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Data
@AllArgsConstructor
@Accessors(fluent = true, chain = true)
public class LoginOperator {

    protected Driver driver;
    private String token;

    public LoginOperator(Driver driver) {
        this.driver = driver;
    }

    public LoginOperator login(String username, String password) throws Exception {

        AuthenticationRequest authReq = AuthenticationRequest.builder()
                .username(username)
                .password(password)
                .build();


        MockHttpServletResponse response = driver.mockMvc()
                .perform(
                        post("/authenticate/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(driver.objectMapper().writeValueAsString(authReq))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.jwt").exists())
                .andReturn()
                .getResponse();

        this.token = driver.objectMapper().readValue(response.getContentAsString(), TokenResponseDto.class).getJwt();

        return this;
    }
}
