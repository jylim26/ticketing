package com.ticketing.user.domain;

import java.time.Instant;

import com.ticketing.shared.types.AuthProvider;

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

	private Instant createdAt;

	private Instant updatedAt;

	protected User() {
	}

	private User(String email, AuthProvider authProvider, String nickname) {
		this.email = email;
		this.authProvider = authProvider;
		this.nickname = nickname;
	}

	public static User create(String email, AuthProvider authProvider, String nickname) {
		return new User(email, authProvider, nickname);
	}
}
