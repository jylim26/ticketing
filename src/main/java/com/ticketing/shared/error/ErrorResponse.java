package com.ticketing.shared.error;

import org.springframework.http.HttpStatus;

public class ErrorResponse<T> {
	private Integer status;
	private String message;
	private String code;
	private T data;

	private ErrorResponse(HttpStatus status, String message, String code, T data) {
		this.status = status.value();
		this.message = message;
		this.code = code;
		this.data = data;
	}

	public static <T> ErrorResponse<T> error(ErrorCode code) {
		return new ErrorResponse<>(code.status(), code.message(), code.name(), null);
	}

	public static <T> ErrorResponse<T> error(ErrorCode code, T data) {
		return new ErrorResponse<>(code.status(), code.message(), code.name(), data);
	}
}
