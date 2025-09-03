package com.ticketing.user.infrastructure.persistence.jpa;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.ticketing.user.domain.Email;
import com.ticketing.user.domain.User;
import com.ticketing.user.domain.UserId;
import com.ticketing.user.domain.UserRepository;
import com.ticketing.user.infrastructure.persistence.jpa.entity.UserEntity;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepository {

	private final UserJpaRepository jpaRepository;
	private final UserMapper mapper;

	@Override
	public Optional<User> findById(UserId id) {
		return jpaRepository.findById(id.getValue())
			.map(mapper::toDomain);
	}

	@Override
	public Optional<Long> findIdByEmail(Email email) {
		return jpaRepository.findIdByEmail(email.getValue());
	}

	@Override
	public User save(User user) {
		UserEntity saved = jpaRepository.save(mapper.toEntity(user));
		return mapper.toDomain(saved);
	}
}
