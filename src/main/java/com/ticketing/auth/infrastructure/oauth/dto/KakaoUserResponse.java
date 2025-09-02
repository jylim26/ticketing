package com.ticketing.auth.infrastructure.oauth.dto;

public record KakaoUserResponse(Long id, KakaoAcount kakao_account) {
	public record KakaoAcount(String email, Profile profile) {
		public record Profile(String nickname) {
		}
	}
}
