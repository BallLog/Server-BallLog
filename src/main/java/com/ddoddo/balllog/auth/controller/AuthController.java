package com.ddoddo.balllog.auth.controller;

import com.ddoddo.balllog.global.response.DataResponseDto;
import com.ddoddo.balllog.jwt.dto.JwtRequestDto;
import com.ddoddo.balllog.jwt.dto.JwtResponseDto;
import com.ddoddo.balllog.jwt.service.JwtTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtTokenService jwtTokenService;

    @PostMapping("/reissue")
    public DataResponseDto<JwtResponseDto> reissue(@RequestBody JwtRequestDto jwtRequest) {
        return DataResponseDto.from(jwtTokenService.reissueByRefreshToken(jwtRequest));
    }
}
