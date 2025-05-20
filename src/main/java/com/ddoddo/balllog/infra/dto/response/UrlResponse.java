package com.ddoddo.balllog.infra.dto.response;

import lombok.Builder;

@Builder
public record UrlResponse(
        String url
) {
    public static UrlResponse from(String url) {
        return UrlResponse.builder()
                .url(url)
                .build();
    }
}
