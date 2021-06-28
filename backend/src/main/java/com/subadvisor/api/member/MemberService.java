package com.subadvisor.api.member;

import com.subadvisor.CustomMapper;
import com.subadvisor.DataAccess;
import com.subadvisor.api.member.dto.MemberDto;
import com.subadvisor.api.member.dto.MemberRegistrateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

/**
 * Spring-Service responsible to retrieve Users
 */

@Service
public class MemberService implements UserDetailsService, IMemberService {

    @Autowired
    DataAccess DATA;

    @Autowired
    private CustomMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return DATA.members()
                .findByUsername(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                format("User with username - %s, not found", username)
                        )
                );
    }

    @Override
    public MemberDto registrateMember(MemberRegistrateDto memberRegistrateDto) {
        return mapper.memberToMemberDto(
                DATA.members().save(
                        mapper.memberRegistrateDtoToMember(memberRegistrateDto)
                )
        );
    }


}


