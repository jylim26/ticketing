package com.ticketing.venue.application.service;

import static com.ticketing.venue.application.port.in.dto.CreateVenueCommand.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ticketing.venue.application.port.in.VenueCommandUseCase;
import com.ticketing.venue.application.port.in.dto.CreateVenueCommand;
import com.ticketing.venue.application.port.out.SeatRepositoryPort;
import com.ticketing.venue.application.port.out.SectionRepositoryPort;
import com.ticketing.venue.application.port.out.VenueRepositoryPort;
import com.ticketing.venue.domain.Seat;
import com.ticketing.venue.domain.Section;
import com.ticketing.venue.domain.Venue;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VenueCommandService implements VenueCommandUseCase {

	private final VenueRepositoryPort venueRepositoryPort;
	private final SectionRepositoryPort sectionRepositoryPort;
	private final SeatRepositoryPort seatRepositoryPort;

	@Transactional
	@Override
	public Long createVenue(CreateVenueCommand cmd) {
		Venue venue = Venue.create(cmd.name(), cmd.address(), cmd.capacity(), cmd.geoLat(), cmd.geoLng(), cmd.phone());

		List<SectionSpec> sections = cmd.sections();

		checkSections(sections, venue.getCapacity());

		Venue savedVenue = venueRepositoryPort.save(venue);

		for (SectionSpec s : sections) {
			checkSectionSeatCapacity(s);

			Section savedSection = sectionRepositoryPort.save(
				Section.create(savedVenue.getId(), s.name(), s.capacity()));

			List<Seat> toSave = buildSeatsOrThrowOnDuplicates(savedSection.getId(), s.seats());
			if (!toSave.isEmpty()) {
				seatRepositoryPort.saveAll(toSave);
			}
		}

		return savedVenue.getId();
	}

	private void checkSections(List<SectionSpec> sections, int venueCapacity) {
		Set<String> sectionNames = new HashSet<>();
		for (SectionSpec s : sections) {
			if (!sectionNames.add(s.name())) {
				throw new IllegalArgumentException("요청 내 섹션 이름이 중복되었습니다. name=" + s.name());
			}
		}

		int sumCapacity = sections.stream().mapToInt(SectionSpec::capacity).sum();
		if (sumCapacity > venueCapacity) {
			throw new IllegalArgumentException("섹션 총 수용 인원이 공연장 수용 인원을 초과합니다.");
		}
	}

	private void checkSectionSeatCapacity(SectionSpec section) {
		List<SeatSpec> seats = section.seats();
		if (!seats.isEmpty() && seats.size() > section.capacity()) {
			throw new IllegalArgumentException("좌석 수가 섹션 좌석 수를 초과합니다. section=" + section.name());
		}
	}

	private List<Seat> buildSeatsOrThrowOnDuplicates(Long sectionId, List<SeatSpec> seats) {
		if (seats.isEmpty())
			return List.of();

		Set<String> keys = new HashSet<>(seats.size());
		List<Seat> toSave = new ArrayList<>(seats.size());

		for (SeatSpec seat : seats) {
			String key = seat.rowLabel() + "#" + seat.seatNumber();
			if (!keys.add(key)) {
				throw new IllegalArgumentException(
					"요청 내 중복 좌석. rowLabel=" + seat.rowLabel() + ", seatNumber=" + seat.seatNumber());
			}
			toSave.add(Seat.create(sectionId, seat.rowLabel(), seat.seatNumber()));
		}
		return toSave;
	}
}
