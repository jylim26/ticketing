package com.ticketing.user.infrastructure.persistence;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.ticketing.user.application.port.out.LoadUserPort;
import com.ticketing.user.application.port.out.SaveUserPort;
import com.ticketing.user.domain.User;
import com.ticketing.user.infrastructure.persistence.jpa.UserJpaRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserPersistenceAdapter implements LoadUserPort, SaveUserPort {

	private final UserJpaRepository jpaRepository;

	@Override
	public Optional<User> findById(Long id) {
		return jpaRepository.findById(id);
	}

	@Override
	public Optional<User> findByEmail(String email) {
		return jpaRepository.findByEmail(email);
	}

	@Override
	public User save(User user) {
		return jpaRepository.save(user);
	}
}
