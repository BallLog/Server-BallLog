package com.ddoddo.balllog.auth.controller;

import com.ddoddo.balllog.auth.dto.response.SignInResponse;
import com.ddoddo.balllog.auth.service.AuthService;
import com.ddoddo.balllog.global.response.DataResponseDto;
import com.ddoddo.balllog.jwt.dto.JwtRequest;
import com.ddoddo.balllog.jwt.dto.JwtResponse;
import com.ddoddo.balllog.jwt.service.JwtTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final JwtTokenService jwtTokenService;

    @PostMapping("/sign-in/{provider}")
    public DataResponseDto<SignInResponse> signIn(
            @PathVariable(name = "provider") String provider,
            @RequestHeader("Authorization") String providerAccessToken) {
        return DataResponseDto.from(authService.signIn(provider, providerAccessToken));
    }

    @PostMapping("/reissue")
    public DataResponseDto<JwtResponse> reissue(@RequestBody JwtRequest jwtRequest) {
        return DataResponseDto.from(jwtTokenService.reissueByRefreshToken(jwtRequest));
    }
}
