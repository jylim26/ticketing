package com.ticketing.user.infrastructure.persistence.jpa;

import org.springframework.stereotype.Component;

import com.ticketing.user.domain.Email;
import com.ticketing.user.domain.User;
import com.ticketing.user.domain.UserId;
import com.ticketing.user.infrastructure.persistence.jpa.entity.UserEntity;

@Component
public class UserMapper {

	public User toDomain(UserEntity e) {
		return User.reconstruct(
			UserId.of(e.getId()),
			new Email(e.getEmail()),
			e.getNickname(),
			e.getCreatedAt(),
			e.getUpdatedAt()
		);
	}

	public UserEntity toEntity(User d) {
		return UserEntity.of(
			d.getId().getValue(),
			d.getEmail().getValue(),
			d.getNickname(),
			d.getCreatedAt(),
			d.getUpdatedAt()
		);
	}
}
