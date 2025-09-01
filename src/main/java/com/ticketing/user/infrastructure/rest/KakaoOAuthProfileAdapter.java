package com.ticketing.user.infrastructure.rest;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RestClient;

import com.ticketing.shared.error.CustomException;
import com.ticketing.user.application.port.out.SocialUserProfilePort;
import com.ticketing.user.application.port.out.dto.OAuthProfile;
import com.ticketing.user.domain.Provider;
import com.ticketing.user.infrastructure.rest.config.OAuthProperties;
import com.ticketing.user.infrastructure.rest.dto.KakaoTokenResponse;
import com.ticketing.user.infrastructure.rest.dto.KakaoUserResponse;
import com.ticketing.user.infrastructure.rest.error.KakaoApiErrorCode;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class KakaoOAuthProfileAdapter implements SocialUserProfilePort {
	private static final String GRANT_TYPE_AUTHORIZATION_CODE = "authorization_code";
	private static final String AUTHORIZATION_HEADER = "Authorization";
	private static final String BEARER_PREFIX = "Bearer ";

	private final OAuthProperties oAuthProperties;
	private final RestClient restClient;

	@Override
	public OAuthProfile fetchUserProfile(String code, String redirectUri) {
		String accessToken = fetchToken(code, redirectUri);

		KakaoUserResponse response = restClient.get()
			.uri(oAuthProperties.getKakao().getApiUri())
			.header(AUTHORIZATION_HEADER, BEARER_PREFIX + accessToken)
			.retrieve()
			.onStatus(HttpStatusCode::isError, this::handleKakaoError)
			.body(KakaoUserResponse.class);

		return new OAuthProfile(Provider.KAKAO.name(), response.kakao_account().email(),
			response.kakao_account().profile().nickname());
	}

	private String fetchToken(String code, String redirectUri) {
		LinkedMultiValueMap<String, String> request = new LinkedMultiValueMap<>();
		request.add("grant_type", GRANT_TYPE_AUTHORIZATION_CODE);
		request.add("client_id", oAuthProperties.getKakao().getClientId());
		request.add("redirect_uri", redirectUri);
		request.add("code", code);

		KakaoTokenResponse response = restClient.post()
			.uri(oAuthProperties.getKakao().getTokenUri())
			.body(request)
			.retrieve()
			.onStatus(HttpStatusCode::isError, this::handleKakaoError)
			.body(KakaoTokenResponse.class);

		return response.access_token();
	}

	private void handleKakaoError(HttpRequest req, ClientHttpResponse res) throws IOException {
		String raw = StreamUtils.copyToString(res.getBody(), StandardCharsets.UTF_8);
		log.error("카카오 API 요청 실패. url={}, status={}, body={}", req.getURI(), res.getStatusCode(), raw);

		throw new CustomException(KakaoApiErrorCode.from(res.getStatusCode()));
	}
}
