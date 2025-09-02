package com.ticketing.shared.response;

import org.springframework.http.HttpStatus;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum SuccessCode implements ResCode {
	OK(HttpStatus.OK, "요청이 성공적으로 처리되었습니다.");

	private final HttpStatus status;
	private final String message;

	@Override
	public HttpStatus status() {
		return this.status;
	}

	@Override
	public String message() {
		return this.message;
	}
}
