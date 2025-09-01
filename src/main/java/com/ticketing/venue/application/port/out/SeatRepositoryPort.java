package com.ticketing.venue.application.port.out;

import java.util.List;

import com.ticketing.venue.domain.Seat;

public interface SeatRepositoryPort {
	List<Seat> saveAll(List<Seat> seats);

	boolean existsInSection(Long sectionId, String rowLabel, int seatNumber);
}
