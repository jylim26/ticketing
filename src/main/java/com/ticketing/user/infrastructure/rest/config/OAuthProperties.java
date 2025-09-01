package com.ticketing.user.infrastructure.rest.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "oauth")
public class OAuthProperties {

	private Kakao kakao;

	@Getter
	@Setter
	public static class Kakao {
		private String clientId;
		private String tokenUri;
		private String apiUri;
		private String redirectUri;
	}
}
