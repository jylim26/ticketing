package com.ticketing.user.domain;

import com.ticketing.shared.error.CustomException;

import lombok.Getter;

@Getter
public class UserId {
	private final Long value;

	private UserId(Long value) {
		this.value = value;
	}

	public static UserId of(Long value) {
		if (value == null || value <= 0)
			throw new CustomException(UserErrorCode.INVALID_USER_ID);
		return new UserId(value);
	}

	public static UserId temporary() {
		return new UserId(null);
	}
}
