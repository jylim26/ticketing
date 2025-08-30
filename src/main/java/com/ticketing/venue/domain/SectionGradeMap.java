package com.ticketing.venue.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class SectionGradeMap {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "section_id", nullable = false)
	private Long sectionId;

	@Column(name = "seat_grade_id", nullable = false)
	private Long seatGradeId;

	protected SectionGradeMap() {
	}

	private SectionGradeMap(Long sectionId, Long seatGradeId) {
		this.sectionId = sectionId;
		this.seatGradeId = seatGradeId;
	}

	public static SectionGradeMap create(Long sectionId, Long seatGradeId) {
		return new SectionGradeMap(sectionId, seatGradeId);
	}
}
