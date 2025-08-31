package com.ticketing.venue.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class SeatGrade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	protected SeatGrade() {
	}

	private SeatGrade(String name) {
		this.name = name;
	}

	public static SeatGrade create(String name) {
		return new SeatGrade(name);
	}
}
