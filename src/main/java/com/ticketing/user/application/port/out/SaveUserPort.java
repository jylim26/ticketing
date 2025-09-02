package com.ticketing.user.application.port.out;

import com.ticketing.user.domain.User;

public interface SaveUserPort {
	User save(User user);
}
