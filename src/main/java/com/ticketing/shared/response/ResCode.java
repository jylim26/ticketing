package com.ticketing.shared.response;

import org.springframework.http.HttpStatus;

public interface ResCode {
	HttpStatus status();

	String message();

	String name();
}

