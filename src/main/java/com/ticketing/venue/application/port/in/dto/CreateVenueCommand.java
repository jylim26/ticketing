package com.ticketing.venue.application.port.in.dto;

import java.util.List;

public record CreateVenueCommand(
	String name,
	String address,
	int capacity,
	double geoLat,
	double geoLng,
	String phone,
	List<SectionSpec> sections
) {
	public record SectionSpec(String name, int capacity, List<SeatSpec> seats) {
	}

	public record SeatSpec(String rowLabel, int seatNumber) {
	}
}
