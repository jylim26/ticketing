package com.ticketing.shared.response;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SuccessResponse<T> {
	private Integer status;
	private String message;
	private String code;
	private T data;

	private SuccessResponse(HttpStatus status, String message, String code, T data) {
		this.status = status.value();
		this.message = message;
		this.code = code;
		this.data = data;
	}

	public static SuccessResponse<Void> success(ResCode code) {
		return new SuccessResponse<>(code.status(), code.message(), code.name(), null);
	}

	public static <T> SuccessResponse<T> success(ResCode code, T data) {
		return new SuccessResponse<>(code.status(), code.message(), code.name(), data);
	}
}
