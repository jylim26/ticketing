package com.ticketing.venue.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ticketing.venue.domain.Seat;

public interface SeatJpaRepository extends JpaRepository<Seat, Long> {
	boolean existsBySectionIdAndRowLabelIgnoreCaseAndSeatNumber(long sectionId, String rowLabel, int seatNumber);
}
