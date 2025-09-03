package com.ticketing.user.application;

import java.time.Clock;

import org.springframework.stereotype.Service;

import com.ticketing.user.application.port.in.exposed.RegisterUserUseCase;
import com.ticketing.user.application.port.in.exposed.dto.UserProfileView;
import com.ticketing.user.application.port.in.internal.UserCommandUseCase;
import com.ticketing.user.domain.User;
import com.ticketing.user.domain.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class UserCommandService implements UserCommandUseCase, RegisterUserUseCase {

	private final UserRepository userRepository;
	private final Clock clock;

	@Override
	public UserProfileView registerNewUser(String email, String nickname) {
		User newUser = User.create(email, nickname, clock.instant());

		User savedUser = userRepository.save(newUser);

		return new UserProfileView(
			savedUser.getId().getValue(),
			savedUser.getEmail().getValue(),
			savedUser.getNickname()
		);
	}
}
