package com.ddoddo.balllog.auth.controller;

import com.ddoddo.balllog.auth.dto.response.SignInResponseDto;
import com.ddoddo.balllog.auth.service.AuthService;
import com.ddoddo.balllog.global.response.DataResponseDto;
import com.ddoddo.balllog.jwt.dto.JwtRequestDto;
import com.ddoddo.balllog.jwt.dto.JwtResponseDto;
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
    public DataResponseDto<SignInResponseDto> signIn(
            @PathVariable(name = "provider") String provider,
            @RequestHeader("Authorization") String providerAccessToken) {
        return DataResponseDto.from(authService.signIn(provider, providerAccessToken));
    }

    @PostMapping("/reissue")
    public DataResponseDto<JwtResponseDto> reissue(@RequestBody JwtRequestDto jwtRequest) {
        return DataResponseDto.from(jwtTokenService.reissueByRefreshToken(jwtRequest));
    }
}
