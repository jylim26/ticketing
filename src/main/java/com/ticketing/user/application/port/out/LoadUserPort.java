package com.ticketing.user.application.port.out;

import java.util.Optional;

import com.ticketing.user.domain.User;

public interface LoadUserPort {
	Optional<User> findById(Long id);

	Optional<User> findByEmail(String email);
}
