package com.ticketing.venue.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(name = "uq_seat_in_section",
	columnNames = {"section_id", "row_label", "seat_number"}))
public class Seat {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "section_id", nullable = false)
	private Long sectionId;

	@Column(name = "row_label", nullable = false)
	private String rowLevel;

	@Column(name = "seat_number", nullable = false)
	private Integer seatNumber;

	protected Seat() {
	}

	private Seat(Long sectionId, String rowLabel, Integer seatNumber) {
		if (seatNumber <= 0)
			throw new IllegalArgumentException("좌석 번호는 0이상이어야 합니다.");
		this.sectionId = sectionId;
		this.rowLevel = rowLabel;
		this.seatNumber = seatNumber;
	}

	public static Seat create(Long sectionId, String rowLabel, Integer seatNumber) {
		return new Seat(sectionId, rowLabel, seatNumber);
	}
}
