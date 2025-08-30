package com.ticketing.venue.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Venue {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String address;
	private int capacity;
	private double geoLat;
	private double geoLng;
	private String phone;

	protected Venue() {
	}

	private Venue(String name, String address, int capacity, double lat, double lng, String phone) {
		if (capacity <= 0)
			throw new IllegalArgumentException("공연장 수용 인원은 0이상이어야 합니다.");
		this.name = name;
		this.address = address;
		this.capacity = capacity;
		this.geoLat = lat;
		this.geoLng = lng;
		this.phone = phone;
	}

	public static Venue create(String name, String address, int capacity, double lat, double lng, String phone) {
		return new Venue(name, address, capacity, lat, lng, phone);
	}
}
