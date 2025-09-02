package com.ticketing.auth.application.port.in;

import com.ticketing.auth.application.port.in.dto.TokenPairView;

public interface AuthCommandUseCase {
	TokenPairView socialLogin(String code);

	void logout(String refreshToken);
}
