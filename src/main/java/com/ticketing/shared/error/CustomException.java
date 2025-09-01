package com.ticketing.shared.error;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
	private final ErrorCode errorCode;

	public CustomException(ErrorCode errorCode) {
		super(errorCode.message());
		this.errorCode = errorCode;
	}

	public CustomException(ErrorCode errorCode, Throwable cause) {
		super(errorCode.message(), cause);
		this.errorCode = errorCode;
	}
}
