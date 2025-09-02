package com.ticketing.auth.application.port.out;

import com.ticketing.auth.application.port.out.dto.OAuthProfile;

public interface IdentityProviderPort {
	OAuthProfile fetchProfile(String code);
}
