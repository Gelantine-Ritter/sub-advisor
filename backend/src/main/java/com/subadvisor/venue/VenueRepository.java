package com.subadvisor.venue;

import com.subadvisor.auth.IUserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VenueRepository extends JpaRepository<Venue, Long>, IUserAccount {

    Venue findByName(String name);

}