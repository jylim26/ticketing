package com.ticketing.venue.application.port.out;

import java.util.List;
import java.util.Optional;

import com.ticketing.venue.domain.Section;

public interface SectionRepository {
	Section save(Section section);

	List<Section> findByVenueId(Long venueId);

	boolean existsByVenueIdAndNameIgnoreCase(Long venueId, String name);

	Optional<Section> findById(Long id);
}
