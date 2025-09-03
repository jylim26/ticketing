package com.ticketing.user.domain;

import java.util.Optional;

public interface UserRepository {
	User save(User user);

	Optional<User> findById(UserId id);

	Optional<Long> findIdByEmail(Email email);
}
