package com.ticketing.auth.infrastructure.token.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "security.jwt")
public record JwtProperties(
	String issuer,
	String secret,
	long accessTtlSeconds,
	long refreshTtlSeconds
) {
}
