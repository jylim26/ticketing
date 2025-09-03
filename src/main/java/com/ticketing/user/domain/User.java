package com.ticketing.user.domain;

import java.time.Instant;

import lombok.Getter;

@Getter
public final class User {
	private final UserId id;
	private final Email email;
	private final String nickname;
	private final Instant createdAt;
	private final Instant updatedAt;

	private User(UserId id, Email email, String nickname, Instant createdAt, Instant updatedAt) {
		this.id = id;
		this.email = email;
		this.nickname = nickname;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public static User create(String email, String nickname, Instant now) {
		return new User(UserId.temporary(), new Email(email), nickname, now, now);
	}

	public static User reconstruct(UserId id, Email email, String nickname, Instant createdAt, Instant updatedAt) {
		return new User(id, email, nickname, createdAt, updatedAt);
	}
}
