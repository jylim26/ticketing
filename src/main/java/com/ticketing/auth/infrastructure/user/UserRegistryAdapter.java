package com.ticketing.auth.infrastructure.user;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import com.ticketing.auth.application.port.out.UserRegistryPort;
import com.ticketing.shared.types.AuthProvider;
import com.ticketing.user.application.port.in.exposed.GetUserInfoUseCase;
import com.ticketing.user.application.port.in.exposed.RegisterUserUseCase;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserRegistryAdapter implements UserRegistryPort {

	private final RegisterUserUseCase registerUserUseCase;
	private final GetUserInfoUseCase getUserInfoUseCase;

	@Override
	public Long registerIfAbsent(String email, AuthProvider provider, String nickname) {
		return getUserInfoUseCase.findIdByEmail(email).orElseGet(() -> {
			try {
				return registerUserUseCase.registerNewUser(email, provider, nickname).id();
			} catch (DataIntegrityViolationException e) {
				return getUserInfoUseCase.findIdByEmail(email).orElseThrow(() -> e);
			}
		});
	}
}
