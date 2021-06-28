package com.subadvisor.api.member;

import com.subadvisor.api.member.dto.MemberDto;
import com.subadvisor.api.member.dto.MemberRegistrateDto;
import com.subadvisor.api.member.dto.MemberUpdateDto;
import com.subadvisor.api.venue.dto.VenuePersonalDto;
import com.subadvisor.api.venue.dto.VenueUpdateDto;

public interface IMemberService {
    MemberDto registrateMember(MemberRegistrateDto memberRegistrateDto);
    MemberDto getMemberById(Long memberId);
    MemberDto updateMemberById(MemberUpdateDto memberUpdateDto, Long memberId);
    void deleteMemberById(Long memberId);
}
