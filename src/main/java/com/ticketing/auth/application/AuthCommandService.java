package com.ticketing.auth.application;

import org.springframework.stereotype.Service;

import com.ticketing.auth.application.port.in.AuthCommandUseCase;
import com.ticketing.auth.application.port.in.dto.TokenPairView;
import com.ticketing.auth.application.port.out.IdentityProviderPort;
import com.ticketing.auth.application.port.out.TokenIssuerPort;
import com.ticketing.auth.application.port.out.UserRegistryPort;
import com.ticketing.auth.application.port.out.dto.OAuthProfile;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthCommandService implements AuthCommandUseCase {

	private final IdentityProviderPort identityProviderPort;
	private final UserRegistryPort userRegistryPort;
	private final TokenIssuerPort tokenIssuerPort;

	@Override
	public TokenPairView socialLogin(String code) {
		OAuthProfile profile = identityProviderPort.fetchProfile(code);

		Long userId = userRegistryPort.registerIfAbsent(profile.email(), profile.nickname());

		return tokenIssuerPort.issueToken(userId);
	}

	@Override
	public void logout(String refreshToken) {

	}
}
