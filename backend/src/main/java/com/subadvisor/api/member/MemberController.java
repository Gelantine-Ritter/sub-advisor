package com.subadvisor.api.member;

import com.subadvisor.api.event.EventService;
import com.subadvisor.api.member.dto.MemberRegistrateDto;
import com.subadvisor.security.CreatorCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    @Autowired
    MemberService memberService;

    @Autowired
    CreatorCheck creatorCheck;

    @PostMapping("/members")
    public ResponseEntity<?> registrateMember(@RequestBody MemberRegistrateDto memberRegistrateDto) {
        return new ResponseEntity<>(
            memberService.registrateMember(memberRegistrateDto),
                HttpStatus.OK
        );
    }
}
