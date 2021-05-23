package com.subadvisor.api.user;

import com.subadvisor.api.auth.IUserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, IUserAccount {
}
