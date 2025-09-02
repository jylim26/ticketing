package com.ticketing.auth.infrastructure.token.error;

import org.springframework.http.HttpStatus;

import com.ticketing.shared.error.ErrorCode;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum TokenErrorCode implements ErrorCode {
	INVALID_USER_ID(HttpStatus.BAD_REQUEST, "userId는 필수 값입니다."),
	SECRET_TOO_SHORT(HttpStatus.INTERNAL_SERVER_ERROR, "JWT 비밀키는 Base64 디코딩 후 256비트(32바이트) 이상이어야 합니다.");

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
