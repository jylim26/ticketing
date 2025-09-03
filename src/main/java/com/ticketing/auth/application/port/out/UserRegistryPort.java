package com.ticketing.auth.application.port.out;

public interface UserRegistryPort {
	Long registerIfAbsent(String email, String nickname);
}
