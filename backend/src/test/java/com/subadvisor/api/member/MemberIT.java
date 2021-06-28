package com.subadvisor.api.member;

import com.subadvisor.api.Driver;
import com.subadvisor.api.member.dto.MemberDto;
import com.subadvisor.api.member.dto.MemberRegistrateDto;
import com.subadvisor.api.member.dto.MemberUpdateDto;
import com.subadvisor.operators.LoginOperator;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.springframework.test.web.servlet.ResultMatcher.matchAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


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

        memberUpdateDto_nayla = MemberUpdateDto.builder()
                .firstName(MEMBER_NAYLA_FIRSTNAME)
                .lastName(MEMBER_NAYLA_SECONDNAME)
                .build();

    }

    @Test
    @Order(1)
    void guestCanCreateMemberAccount() throws Exception {

        MockHttpServletResponse response = DRIVER.mockMvc()
                .perform(
                        post("/members")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(memberRegistrateDto_nayla))
                )
                .andDo(print())
                .andExpect(
                        matchAll(
                                jsonPath("$.id").exists(),
                                jsonPath("$.username").value(MEMBER_NAYLA_USERNAME),
                                jsonPath("$.email").value(MEMBER_NAYLA_EMAIL),
                                jsonPath("$.firstName").doesNotExist(),
                                jsonPath("$.lastName").doesNotExist(),
                                jsonPath("$.role").value("MEMBER"),
                                status().isOk()
                        ))
                .andReturn()
                .getResponse();

        memberDto_nayla = objectMapper.readValue(response.getContentAsString(), MemberDto.class);

        TOKEN_MEMBER_NAYLA = new LoginOperator(DRIVER).login(MEMBER_NAYLA_USERNAME, MEMBER_NAYLA_PASSWORD).token();
    }

    @Test
    @Order(2)
    void guestCanUpdateOwnProfile() throws Exception {

        DRIVER.mockMvc()
                .perform(
                        put("/members/" + memberDto_nayla.getId().toString())
                                .header("authorization", "Bearer " + TOKEN_MEMBER_NAYLA)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(memberUpdateDto_nayla))
                )
                .andDo(print())
                .andExpect(
                        matchAll(
                                jsonPath("$.id").value(memberDto_nayla.getId()),
                                jsonPath("$.username").value(MEMBER_NAYLA_USERNAME),
                                jsonPath("$.email").value(MEMBER_NAYLA_EMAIL),
                                jsonPath("$.firstName").value(MEMBER_NAYLA_FIRSTNAME),
                                jsonPath("$.lastName").value(MEMBER_NAYLA_SECONDNAME),
                                jsonPath("$.role").value("MEMBER"),
                                status().isOk()
                        ))
                .andReturn()
                .getResponse();
    }

    @Test
    @Order(3)
    void memberCanJoinAnEvent() throws Exception {

        DRIVER.mockMvc()
                .perform(
                        put("/members/" + memberDto_nayla.getId().toString())
                                .param("joinEvent", "1")
                                .header("authorization", "Bearer " + TOKEN_MEMBER_NAYLA)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(memberUpdateDto_nayla))
                )
                .andDo(print())
                .andExpect(
                        matchAll(
                                jsonPath("$.id").value(memberDto_nayla.getId()),
                                jsonPath("$.username").value(MEMBER_NAYLA_USERNAME),
                                jsonPath("$.email").value(MEMBER_NAYLA_EMAIL),
                                jsonPath("$.firstName").value(MEMBER_NAYLA_FIRSTNAME),
                                jsonPath("$.lastName").value(MEMBER_NAYLA_SECONDNAME),
                                jsonPath("$.role").value("MEMBER"),
                                jsonPath("$.events").isMap(),
                                jsonPath("$.events", Matchers.hasKey("1")),
                                status().isOk()
                        )
                );
    }

    @Test
    @Order(4)
    void memberCanLeaveEvent() throws Exception {

        DRIVER.mockMvc()
                .perform(
                        put("/members/" + memberDto_nayla.getId().toString())
                                .param("leaveEvent", "1")
                                .header("authorization", "Bearer " + TOKEN_MEMBER_NAYLA)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(memberUpdateDto_nayla))
                )
                .andDo(print())
                .andExpect(
                        matchAll(
                                jsonPath("$.id").value(memberDto_nayla.getId()),
                                jsonPath("$.username").value(MEMBER_NAYLA_USERNAME),
                                jsonPath("$.email").value(MEMBER_NAYLA_EMAIL),
                                jsonPath("$.firstName").value(MEMBER_NAYLA_FIRSTNAME),
                                jsonPath("$.lastName").value(MEMBER_NAYLA_SECONDNAME),
                                jsonPath("$.role").value("MEMBER"),
                                jsonPath("$.events").value(""),
                                status().isOk()
                        )
                );

    }



    @Test
    @Order(5)
    void guestCanGetOwnProfile() throws Exception {

        DRIVER.mockMvc()
                .perform(
                        get("/members/" + memberDto_nayla.getId().toString())
                                .header("authorization", "Bearer " + TOKEN_MEMBER_NAYLA)
                )
                .andDo(print())
                .andExpect(
                        matchAll(
                                jsonPath("$.id").value(memberDto_nayla.getId()),
                                jsonPath("$.username").value(MEMBER_NAYLA_USERNAME),
                                jsonPath("$.email").value(MEMBER_NAYLA_EMAIL),
                                jsonPath("$.firstName").value(MEMBER_NAYLA_FIRSTNAME),
                                jsonPath("$.lastName").value(MEMBER_NAYLA_SECONDNAME),
                                jsonPath("$.role").value("MEMBER"),
                                status().isOk()
                        )
                );
    }

    @Test
    @Order(6)
    void otherGuestCannotGetThisProfile() throws Exception {
        DRIVER.mockMvc()
                .perform(
                        get("/members/" + memberDto_nayla.getId().toString())
                )
                .andDo(print())
                .andExpect(
                        status().is4xxClientError()

                );

    }


    @Test
    @Order(7)
    void otherGuestCannotDeleteThisProfile() throws Exception {
        DRIVER.mockMvc()
                .perform(
                        delete("/members/" + memberDto_nayla.getId().toString())
                )
                .andDo(print())
                .andExpect(
                        status().is4xxClientError()

                );
    }


    @Test
    @Order(8)
    void guestCanDeleteOwnProfile() throws Exception {
        DRIVER.mockMvc()
                .perform(
                        delete("/members/" + memberDto_nayla.getId().toString())
                                .header("authorization", "Bearer " + TOKEN_MEMBER_NAYLA)
                )
                .andDo(print())
                .andExpect(
                        status().isOk()
                );
        Assertions.assertEquals(Optional.empty(), memberRepository().findById(memberDto_nayla.getId()));
    }
}
