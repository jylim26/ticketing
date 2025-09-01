package com.ticketing.user.infrastructure.rest.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import com.ticketing.shared.error.ErrorCode;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum KakaoApiErrorCode implements ErrorCode {
	KAKAO_UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "로그인이 만료되었어요. 다시 로그인해 주세요."),
	KAKAO_FORBIDDEN(HttpStatus.FORBIDDEN, "카카오 접근 권한이 없어요."),
	KAKAO_UNAVAILABLE(HttpStatus.SERVICE_UNAVAILABLE, "카카오 서버 연결이 원활하지 않아요. 잠시 후 다시 시도해 주세요."),

	KAKAO_API_ERROR(HttpStatus.BAD_GATEWAY, "카카오 API 처리 중 오류가 발생했어요.");

	private final HttpStatus status;
	private final String message;

	public static KakaoApiErrorCode from(HttpStatusCode status) {
		int s = status.value();
		if (s == 401)
			return KAKAO_UNAUTHORIZED;
		if (s == 403)
			return KAKAO_FORBIDDEN;
		if (s == 500 || s == 502 || s == 503 || s == 504)
			return KAKAO_UNAVAILABLE;

		return KAKAO_API_ERROR;
	}

	@Override
	public HttpStatus status() {
		return this.status;
	}

	@Override
	public String message() {
		return this.message;
	}
}
