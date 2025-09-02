package com.ticketing.auth.application.port.out.dto;

import com.ticketing.shared.types.AuthProvider;

public record OAuthProfile(AuthProvider provider, String email, String nickname) {
}
