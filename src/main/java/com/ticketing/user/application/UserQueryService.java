package com.ticketing.user.application;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ticketing.user.application.port.in.exposed.GetUserInfoUseCase;
import com.ticketing.user.application.port.in.internal.UserQueryUseCase;
import com.ticketing.user.application.port.out.LoadUserPort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class UserQueryService implements UserQueryUseCase, GetUserInfoUseCase {

	private final LoadUserPort loadUserPort;

	@Override
	public Optional<Long> findIdByEmail(String email) {
		return loadUserPort.findIdByEmail(email);
	}
}
