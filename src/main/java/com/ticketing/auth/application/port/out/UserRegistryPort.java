package com.ticketing.auth.application.port.out;

import com.ticketing.shared.types.AuthProvider;

public interface UserRegistryPort {
	Long registerIfAbsent(String email, AuthProvider provider, String nickname);
}
