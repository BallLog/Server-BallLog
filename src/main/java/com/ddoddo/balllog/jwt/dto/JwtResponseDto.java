package com.ddoddo.balllog.jwt.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JwtResponseDto {
    private String grantType;
    private String accessToken;
    private String refreshToken;
    private long accessTokenExpiresIn;
    private long refreshTokenExpiresIn;
}
