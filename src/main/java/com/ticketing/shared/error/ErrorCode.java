package com.ticketing.shared.error;

import org.springframework.http.HttpStatus;

public interface ErrorCode {
	HttpStatus status();

	String message();

	String name();
}
