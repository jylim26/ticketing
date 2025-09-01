package com.ticketing.venue.application.port.out;

import java.util.Optional;

import com.ticketing.venue.domain.Venue;

public interface VenueRepositoryPort {
	Venue save(Venue venue);

	Optional<Venue> findById(Long id);
}
