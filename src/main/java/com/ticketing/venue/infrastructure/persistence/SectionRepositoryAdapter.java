package com.ticketing.venue.infrastructure.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.ticketing.venue.application.port.out.SectionRepository;
import com.ticketing.venue.domain.Section;
import com.ticketing.venue.infrastructure.persistence.jpa.SectionJpaRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class SectionRepositoryAdapter implements SectionRepository {

	private final SectionJpaRepository jpa;

	@Override
	public Section save(Section section) {
		return jpa.save(section);
	}

	@Override
	public List<Section> findByVenueId(Long venueId) {
		return jpa.findByVenueId(venueId);
	}

	@Override
	public boolean existsByVenueIdAndNameIgnoreCase(Long venueId, String name) {
		return jpa.existsByVenueIdAndNameIgnoreCase(venueId, name);
	}

	@Override
	public Optional<Section> findById(Long id) {
		return jpa.findById(id);
	}
}
