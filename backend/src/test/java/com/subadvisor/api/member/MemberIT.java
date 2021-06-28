package com.subadvisor.api.member;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.subadvisor.api.Driver;
import com.subadvisor.api.member.dto.MemberDto;
import com.subadvisor.api.member.dto.MemberRegistrateDto;
import com.subadvisor.api.member.dto.MemberUpdateDto;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.springframework.test.web.servlet.ResultMatcher.matchAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MemberIT extends Driver {

    private Driver DRIVER = driver();
    private static MemberRegistrateDto memberRegistrateDto_nayla;
    private static MemberDto memberDto_nayla;
    private static MemberUpdateDto memberUpdateDto_nayla;
    private static String TOKEN_MEMBER_NAYLA;

    private static final String MEMBER_NAYLA_USERNAME = "Nayla";
    private static final String MEMBER_NAYLA_FIRSTNAME = "Nayla";
    private static final String MEMBER_NAYLA_SECONDNAME = "Hammerschlag";
    private static final String MEMBER_NAYLA_PASSWORD = "hammer";
    private static final String MEMBER_NAYLA_EMAIL = "hammerschlag@gmail.com";
    private static final String ID_MEMBER_NAYLA = null;


    @BeforeAll
    void setUp() {

        memberRegistrateDto_nayla = MemberRegistrateDto.builder()
                .username(MEMBER_NAYLA_USERNAME)
                .email(MEMBER_NAYLA_EMAIL)
                .password(MEMBER_NAYLA_PASSWORD)
                .build();

    }

    @Test
    void guestCanCreateMemberAccount() throws Exception {
        DRIVER.mockMvc()
                .perform(
                        post("/members")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(memberRegistrateDto_nayla))
                )
                .andDo(print())
                .andReturn()
                .getResponse();

    }


}
