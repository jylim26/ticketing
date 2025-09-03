package com.ticketing.user.domain;

import org.springframework.http.HttpStatus;

import com.ticketing.shared.error.ErrorCode;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum UserErrorCode implements ErrorCode {
	INVALID_USER_ID(HttpStatus.UNAUTHORIZED, "회원 식별키는 null이거나 0보다 작을 수 없습니다."),
	INVALID_EMAIL(HttpStatus.UNAUTHORIZED, "이메일은 null이거나 공백일 수 없습니다."),
	USER_NOT_FOUND(HttpStatus.UNAUTHORIZED, "회원 엔티티를 찾을 수 없습니다.");

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
