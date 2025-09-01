package com.ticketing.user.application.port.out.dto;

public record OAuthProfile(String provider, String email, String nickname) {
}
