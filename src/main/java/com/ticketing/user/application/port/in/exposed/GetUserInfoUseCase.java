package com.ticketing.user.application.port.in.exposed;

import java.util.Optional;

public interface GetUserInfoUseCase {
	Optional<Long> findIdByEmail(String email);
}
