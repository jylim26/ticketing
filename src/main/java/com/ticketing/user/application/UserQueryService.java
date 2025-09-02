package com.ticketing.user.application;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ticketing.shared.error.CustomException;
import com.ticketing.user.application.port.in.exposed.GetUserInfoUseCase;
import com.ticketing.user.application.port.in.internal.UserQueryUseCase;
import com.ticketing.user.application.port.out.LoadUserPort;
import com.ticketing.user.domain.User;
import com.ticketing.user.domain.UserErrorCode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class UserQueryService implements UserQueryUseCase, GetUserInfoUseCase {

	private final LoadUserPort loadUserPort;

	@Override
	public Optional<Long> findByEmail(String email) {
		User foundUser = loadUserPort.findByEmail(email)
			.orElseThrow(() -> new CustomException(UserErrorCode.USER_NOT_FOUND));

		return Optional.of(foundUser.getId());
	}
}
