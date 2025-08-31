package com.ticketing.venue.application.port.in;

import com.ticketing.venue.application.port.in.dto.CreateVenueCommand;

public interface VenueCommandUseCase {
	Long createVenue(CreateVenueCommand cmd);
}
