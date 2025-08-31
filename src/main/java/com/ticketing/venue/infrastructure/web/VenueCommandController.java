package com.ticketing.venue.infrastructure.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ticketing.venue.application.port.in.VenueCommandUseCase;
import com.ticketing.venue.application.port.in.dto.CreateVenueCommand;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/venues")
@RequiredArgsConstructor
public class VenueCommandController {

	private final VenueCommandUseCase useCase;

	@PostMapping
	public Long create(@RequestBody CreateVenueCommand cmd) {
		return useCase.createVenue(cmd);
	}
}
