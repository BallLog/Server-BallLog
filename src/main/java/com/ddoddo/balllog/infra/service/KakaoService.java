package com.ddoddo.balllog.infra.service;

import com.ddoddo.balllog.global.exception.ErrorCode;
import com.ddoddo.balllog.global.exception.InternalServerException;
import com.ddoddo.balllog.infra.model.KakaoResource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;


@Service
@RequiredArgsConstructor
@Slf4j
public class KakaoService {
    @Value("${security.oauth2.provider.kakao.user-info-uri}")
    private String kakaoUserInfoUri;
    @Value("${security.oauth2.provider.kakao.unlink-uri}")
    private String kakaoUnlinkUri;
    @Value("${security.oauth2.provider.kakao.client-id}")
    private String kakaoClientId;
    @Value("${security.oauth2.provider.kakao.admin-key}")
    private String kakaoAdminKey;


    public KakaoResource getKaKaoAccountInfo(String kakaoAccessToken) {
        String bearerPrefix = "Bearer ";
        WebClient webClient = WebClient.builder()
                .baseUrl(kakaoUserInfoUri)
                .defaultHeader(HttpHeaders.AUTHORIZATION, bearerPrefix + kakaoAccessToken)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .build();

        try {
            return webClient.get()
                    .retrieve()
                    .bodyToMono(KakaoResource.class)
                    .block();
        } catch (WebClientException e) {
            throw new InternalServerException(ErrorCode.KAKAO_SERVER_ERROR);
        }
    }

    public void unlinkKakaoAccount(String kakaoAccountId) {
        WebClient webClient = WebClient.builder()
                .baseUrl(kakaoUnlinkUri)
                .defaultHeader(HttpHeaders.AUTHORIZATION, "KakaoAK" + kakaoAdminKey)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .build();

        MultiValueMap<String, String> bodyData = new LinkedMultiValueMap<>();
        bodyData.add("target_id_type", "user_id");
        bodyData.add("target_id", kakaoAccountId);

        try {
            webClient.post()
                    .body(BodyInserters.fromFormData(bodyData))
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
        } catch (WebClientException e) {
            throw new InternalServerException(ErrorCode.KAKAO_SERVER_ERROR);
        }
    }
}
