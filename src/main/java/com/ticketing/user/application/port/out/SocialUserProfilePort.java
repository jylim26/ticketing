package com.ticketing.user.application.port.out;

import com.ticketing.user.application.port.out.dto.OAuthProfile;

public interface SocialUserProfilePort {
	OAuthProfile fetchUserProfile(String code, String redirectUri);
}
