package com.ddoddo.balllog.auth.dto.response;

import com.ddoddo.balllog.jwt.dto.JwtResponseDto;
import com.ddoddo.balllog.user.model.Status;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SignInResponseDto {
    private Status status;
    private JwtResponseDto tokenInfo;

    public static SignInResponseDto from(Status status) {
        return SignInResponseDto.builder()
                .status(status)
                .build();
    }

    public static SignInResponseDto from(Status status, JwtResponseDto tokenInfo) {
        return SignInResponseDto.builder()
                .status(status)
                .tokenInfo(tokenInfo)
                .build();
    }
}
