package com.subadvisor.api.member;

import com.subadvisor.api.auth.IUserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>, IUserAccount {
}
