package com.ticketing.user.application.port.in.exposed;

import com.ticketing.shared.types.AuthProvider;
import com.ticketing.user.application.port.in.exposed.dto.UserProfileView;

public interface RegisterUserUseCase {
	UserProfileView registerNewUser(String email, AuthProvider provider, String nickname);
}
