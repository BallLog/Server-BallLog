package com.ddoddo.balllog.auth.dto.response;

import com.ddoddo.balllog.jwt.dto.JwtResponse;
import com.ddoddo.balllog.user.model.Status;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SignInResponse {
    private Status status;
    private JwtResponse tokenInfo;

    public static SignInResponse from(Status status) {
        return SignInResponse.builder()
                .status(status)
                .build();
    }

    public static SignInResponse from(Status status, JwtResponse tokenInfo) {
        return SignInResponse.builder()
                .status(status)
                .tokenInfo(tokenInfo)
                .build();
    }
}
