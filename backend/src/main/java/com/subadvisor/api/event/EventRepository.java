package com.subadvisor.api.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByVenueId(Long venueId);
    List<Event> findByVenueNameIgnoreCase(String name);
    List<Event> findByDate(LocalDate date);
    @Query("SELECT DISTINCT e FROM Event e WHERE :tag MEMBER OF e.tags")
    List<Event> findByTag(@Param("tag") String tag);
}
