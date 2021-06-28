package com.subadvisor.api.member;

import com.subadvisor.api.member.dto.MemberDto;
import com.subadvisor.api.member.dto.MemberRegistrateDto;

public interface IMemberService {
    MemberDto registrateMember(MemberRegistrateDto memberRegistrateDto);
}
