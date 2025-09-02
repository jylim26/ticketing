package com.ticketing.user.infrastructure.persistence.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ticketing.user.domain.User;

public interface UserJpaRepository extends JpaRepository<User, Long> {

	@Query("select u.id from User u where u.email = :email")
	Optional<Long> findIdByEmail(@Param("email") String email);
}
