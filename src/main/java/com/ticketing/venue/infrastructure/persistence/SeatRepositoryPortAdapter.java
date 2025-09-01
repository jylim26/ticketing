package com.ticketing.venue.infrastructure.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ticketing.venue.application.port.out.SeatRepositoryPort;
import com.ticketing.venue.domain.Seat;
import com.ticketing.venue.infrastructure.persistence.jpa.SeatJpaRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class SeatRepositoryPortAdapter implements SeatRepositoryPort {

	private final SeatJpaRepository jpa;

	@Override
	public List<Seat> saveAll(List<Seat> seats) {
		return jpa.saveAll(seats);
	}

	@Override
	public boolean existsInSection(Long sectionId, String rowLabel, int seatNumber) {
		return jpa.existsBySectionIdAndRowLabelIgnoreCaseAndSeatNumber(sectionId, rowLabel, seatNumber);
	}
}
