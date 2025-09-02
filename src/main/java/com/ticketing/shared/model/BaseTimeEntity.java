package com.ticketing.shared.model;

import java.time.Clock;
import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class BaseTimeEntity {

	@Column(name = "created_at", nullable = false, updatable = false)
	private Instant createdAt;

	@Column(name = "updated_at")
	private Instant updatedAt;

	public static BaseTimeEntity now(Clock clock) {
		Instant now = Instant.now(clock);
		return new BaseTimeEntity(now, now);
	}

	public BaseTimeEntity touch(Clock clock) {
		Instant now = Instant.now(clock);
		return new BaseTimeEntity(createdAt, now);
	}
}
