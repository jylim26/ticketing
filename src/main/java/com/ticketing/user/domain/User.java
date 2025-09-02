package com.ticketing.user.domain;

import java.time.Clock;

import com.ticketing.shared.model.BaseTimeEntity;
import com.ticketing.shared.types.AuthProvider;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;

@Getter
@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(name = "uk_user_email", columnNames = "email"))
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String email;

	@Enumerated(EnumType.STRING)
	private AuthProvider authProvider;

	private String nickname;

	@Embedded
	private BaseTimeEntity baseTimeEntity;

	protected User() {
	}

	private User(String email, AuthProvider authProvider, String nickname, Clock clock) {
		this.email = email;
		this.authProvider = authProvider;
		this.nickname = nickname;
		this.baseTimeEntity = BaseTimeEntity.now(clock);
	}

	public static User create(String email, AuthProvider authProvider, String nickname, Clock clock) {
		return new User(email, authProvider, nickname, clock);
	}
}
