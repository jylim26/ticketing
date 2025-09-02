package com.ticketing.user.application;

import org.springframework.stereotype.Service;

import com.ticketing.shared.types.AuthProvider;
import com.ticketing.user.application.port.in.exposed.RegisterUserUseCase;
import com.ticketing.user.application.port.in.exposed.dto.UserProfileView;
import com.ticketing.user.application.port.in.internal.UserCommandUseCase;
import com.ticketing.user.application.port.out.SaveUserPort;
import com.ticketing.user.domain.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class UserCommandService implements UserCommandUseCase, RegisterUserUseCase {

	private final SaveUserPort saveUserPort;

	@Override
	public UserProfileView registerNewUser(String email, AuthProvider provider, String nickname) {
		User savedUser = saveUserPort.save(User.create(email, provider, nickname));
		return new UserProfileView(savedUser.getId(), savedUser.getEmail(), savedUser.getNickname());
	}
}
