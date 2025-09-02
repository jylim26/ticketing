package com.ticketing.auth.web;

import java.time.Duration;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ticketing.auth.application.port.in.AuthCommandUseCase;
import com.ticketing.auth.application.port.in.dto.TokenPairView;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthCommandController {
	private static final String COOKIE_ACCESS_TOKEN = "access_token";

	private final AuthCommandUseCase authCommandUseCase;

	@GetMapping("/{provider}/callback")
	public ResponseEntity<Void> handleProviderCallback(
		@PathVariable(name = "provider") String provider, @RequestParam(name = "code") String code) {
		TokenPairView tokens = authCommandUseCase.socialLogin(code);

		ResponseCookie accessCookie = ResponseCookie.from(COOKIE_ACCESS_TOKEN, tokens.accessToken())
			.httpOnly(true)
			.secure(true)
			.sameSite("None")
			.path("/")
			.maxAge(Duration.ofDays(14))
			.build();

		return ResponseEntity
			.status(HttpStatus.SEE_OTHER)
			.header(HttpHeaders.SET_COOKIE, accessCookie.toString())
			.header(HttpHeaders.LOCATION, "/")
			.build();
	}

	@DeleteMapping("/session")
	public ResponseEntity<Void> deleteSession() {
		ResponseCookie cookie = ResponseCookie.from(COOKIE_ACCESS_TOKEN, "")
			.httpOnly(true)
			.secure(true)
			.sameSite("None")
			.path("/")
			.maxAge(0)
			.build();

		return ResponseEntity
			.status(HttpStatus.SEE_OTHER)
			.header(HttpHeaders.SET_COOKIE, cookie.toString())
			.build();
	}
}
