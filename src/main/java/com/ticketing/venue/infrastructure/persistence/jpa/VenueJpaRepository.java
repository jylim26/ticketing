package com.ticketing.venue.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ticketing.venue.domain.Venue;

public interface VenueJpaRepository extends JpaRepository<Venue, Long> {
}
