package com.ticketing.venue.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(name = "uq_section_venue_name",
	columnNames = {"venue_id", "name"}))
public class Section {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "venue_id", nullable = false)
	private Long venueId;

	private String name;
	private int capacity;

	protected Section() {
	}

	private Section(Long venueId, String name, int capacity) {
		if (capacity < 0)
			throw new IllegalArgumentException("구역의 수용 인원은 0이상이어야 합니다.");
		this.venueId = venueId;
		this.name = name;
		this.capacity = capacity;
	}

	public static Section create(Long venueId, String name, int capacity) {
		return new Section(venueId, name, capacity);
	}
}
