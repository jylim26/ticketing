package com.ticketing.auth.application.port.in.dto;

public record TokenPairView(String accessToken, String refreshToken) {
}
