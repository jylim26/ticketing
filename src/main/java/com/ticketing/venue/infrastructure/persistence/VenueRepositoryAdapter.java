package com.ticketing.venue.infrastructure.persistence;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.ticketing.venue.application.port.out.VenueRepository;
import com.ticketing.venue.domain.Venue;
import com.ticketing.venue.infrastructure.persistence.jpa.VenueJpaRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class VenueRepositoryAdapter implements VenueRepository {

	private final VenueJpaRepository jpa;

	@Override
	public Venue save(Venue venue) {
		return jpa.save(venue);
	}

	@Override
	public Optional<Venue> findById(Long id) {
		return jpa.findById(id);
	}
}
