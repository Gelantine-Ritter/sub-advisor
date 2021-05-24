package com.subadvisor.operators;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.subadvisor.api.auth.TokenResponseDto;
import com.subadvisor.api.auth.dto.AuthenticationRequest;
import com.subadvisor.api.member.MemberRepository;
import com.subadvisor.api.venue.VenueRepository;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Builder
@Data
@Accessors(fluent = true, chain = true)
public class LoginOperator extends AbstractOperator {

    private MockMvc mockMvc;
    private VenueRepository venueRepository;
    private MemberRepository memberRepository;
    private ObjectMapper objectMapper;
    private String token;

    public LoginOperator login(String username, String password) throws Exception {

        AuthenticationRequest authReq = AuthenticationRequest.builder()
                .username(username)
                .password(password)
                .build();

        System.out.println(authReq);

        MockHttpServletResponse response = mockMvc
                .perform(
                        post("/authenticate/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(authReq))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.jwt").exists())
                .andReturn()
                .getResponse();

        this.token = objectMapper.readValue(response.getContentAsString(), TokenResponseDto.class).getJwt();

        return this;
    }
}
