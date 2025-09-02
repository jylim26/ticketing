package com.ticketing.auth.infrastructure.token;

import java.security.Key;
import java.sql.Date;
import java.time.Instant;
import java.util.Base64;

import org.springframework.stereotype.Component;

import com.ticketing.auth.application.port.in.dto.TokenPairView;
import com.ticketing.auth.application.port.out.TokenIssuerPort;
import com.ticketing.auth.infrastructure.token.config.JwtProperties;
import com.ticketing.auth.infrastructure.token.error.TokenErrorCode;
import com.ticketing.shared.error.CustomException;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtTokenIssuerAdapter implements TokenIssuerPort {

	private static final String CLAIM_TOKEN_TYPE = "token_type";
	private static final String TOKEN_TYPE_ACCESS = "access";
	private static final String TOKEN_TYPE_REFRESH = "refresh";

	private final JwtProperties props;

	private Key signingKey;

	@PostConstruct
	void init() {
		byte[] keyBytes = Base64.getDecoder().decode(props.secret());
		if (keyBytes.length < 32) {
			throw new CustomException(TokenErrorCode.SECRET_TOO_SHORT);
		}
		this.signingKey = Keys.hmacShaKeyFor(keyBytes);
	}

	@Override
	public TokenPairView issueToken(Long userId) {
		if (userId == null) {
			throw new CustomException(TokenErrorCode.INVALID_USER_ID);
		}

		Instant now = Instant.now();

		String access = buildJwt(userId, now, props.accessTtlSeconds(), TOKEN_TYPE_ACCESS);
		String refresh = buildJwt(userId, now, props.refreshTtlSeconds(), TOKEN_TYPE_REFRESH);

		return new TokenPairView(access, refresh);
	}

	private String buildJwt(Long userId, Instant now, long ttlSeconds, String tokenType) {
		Instant exp = now.plusSeconds(ttlSeconds);
		return Jwts.builder()
			.setHeaderParam("typ", "JWT")
			.setIssuer(props.issuer())
			.setSubject(String.valueOf(userId))
			.setIssuedAt(Date.from(now))
			.setExpiration(Date.from(exp))
			.claim(CLAIM_TOKEN_TYPE, tokenType)
			.signWith(signingKey, SignatureAlgorithm.HS256)
			.compact();
	}
}
