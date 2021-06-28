package com.subadvisor.api.member;

import com.subadvisor.api.event.EventService;
import com.subadvisor.api.member.dto.MemberRegistrateDto;
import com.subadvisor.api.member.dto.MemberUpdateDto;
import com.subadvisor.security.CreatorCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/members/{id}")
    @PreAuthorize("authentication.principal.id == #id || hasRole('ADMIN')")
    public ResponseEntity<?> getMemberById(@PathVariable(value = "id") Long id) {

        System.out.println("hello");
        return new ResponseEntity<>(
                memberService.getMemberById(id),
                HttpStatus.OK
        );
    }

    @PutMapping("/members/{id}")
    @PreAuthorize("authentication.principal.id == #id || hasRole('ADMIN')")
    public ResponseEntity<?> updateMemberById(@RequestBody MemberUpdateDto memberUpdateDto, @PathVariable Long id) {
        return new ResponseEntity<>(
                memberService.updateMemberById(memberUpdateDto, id),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/members/{id}")
    @PreAuthorize("authentication.principal.id == #id || hasRole('ADMIN')")
    public void deleteVenueById(@PathVariable Long id) {
        memberService.deleteMemberById(id);
    }
}
