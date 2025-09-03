package com.ticketing.user.domain;

import com.ticketing.shared.error.CustomException;

import lombok.Getter;

@Getter
public class Email {
	private final String value;

	public Email(String value) {
		if (value == null || value.isBlank())
			throw new CustomException(UserErrorCode.INVALID_EMAIL);
		this.value = value;
	}
}
