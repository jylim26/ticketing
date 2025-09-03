package com.ticketing.user.application;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ticketing.user.application.port.in.exposed.GetUserInfoUseCase;
import com.ticketing.user.application.port.in.internal.UserQueryUseCase;
import com.ticketing.user.domain.Email;
import com.ticketing.user.domain.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class UserQueryService implements UserQueryUseCase, GetUserInfoUseCase {

	private final UserRepository userRepository;

	@Override
	public Optional<Long> findIdByEmail(String email) {
		return userRepository.findIdByEmail(new Email(email));
	}
}
