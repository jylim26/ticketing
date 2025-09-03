package com.ticketing.user.infrastructure.persistence.jpa.entity;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(name = "uk_user_email", columnNames = "email"))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity extends BaseJpaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String email;

	private String nickname;

	private UserEntity(Long id, String email, String nickname, Instant createdAt, Instant updatedAt) {
		super(createdAt, updatedAt);
		this.id = id;
		this.email = email;
		this.nickname = nickname;
	}

	public static UserEntity of(Long id, String email, String nickname, Instant createdAt, Instant updatedAt) {
		return new UserEntity(id, email, nickname, createdAt, updatedAt);
	}
}
