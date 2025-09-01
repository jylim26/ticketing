package com.ticketing.user.infrastructure.rest.dto;

public record KakaoUserResponse(Long id, KakaoAcount kakao_account) {
	public record KakaoAcount(String email, Profile profile) {
		public record Profile(String nickname) {
		}
	}
}
