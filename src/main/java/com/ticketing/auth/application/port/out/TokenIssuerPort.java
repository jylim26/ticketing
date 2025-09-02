package com.ticketing.auth.application.port.out;

import com.ticketing.auth.application.port.in.dto.TokenPairView;

public interface TokenIssuerPort {
	TokenPairView issueToken(Long userId);
}
