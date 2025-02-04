package com.ddoddo.balllog.jwt.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class JwtRequest {
    private String accessToken;
    private String refreshToken;
}
