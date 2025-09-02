package com.ticketing.auth.infrastructure.oauth.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "oauth")
public record OAuthProperties(Kakao kakao) {

	public static record Kakao(String clientId, String tokenUri, String apiUri, String redirectUri) {
	}
}
