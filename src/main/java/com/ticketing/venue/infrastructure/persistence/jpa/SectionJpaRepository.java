package com.ticketing.venue.infrastructure.persistence.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ticketing.venue.domain.Section;

public interface SectionJpaRepository extends JpaRepository<Section, Long> {
	List<Section> findByVenueId(Long venueId);

	boolean existsByVenueIdAndNameIgnoreCase(long venueId, String name);
}
