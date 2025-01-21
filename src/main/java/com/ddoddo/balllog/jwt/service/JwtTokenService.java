package com.ddoddo.balllog.jwt.service;

import com.ddoddo.balllog.auth.userdetails.BallLogUserDetails;
import com.ddoddo.balllog.global.exception.AuthorizationFailedException;
import com.ddoddo.balllog.global.exception.ErrorCode;
import com.ddoddo.balllog.jwt.JwtTokenProvider;
import com.ddoddo.balllog.jwt.dto.JwtRequestDto;
import com.ddoddo.balllog.jwt.dto.JwtResponseDto;
import com.ddoddo.balllog.jwt.model.JwtRefreshToken;
import com.ddoddo.balllog.jwt.repository.JwtRefreshTokenRepository;
import com.ddoddo.balllog.user.model.User;
import com.ddoddo.balllog.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class JwtTokenService {
    private final JwtTokenProvider tokenProvider;
    private final JwtRefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;

    @Transactional
    public JwtResponseDto issueToken(User user) {
        refreshTokenRepository.findFirstByUserId(user.getId()).ifPresent(refreshTokenRepository::delete);
        refreshTokenRepository.flush();

        BallLogUserDetails userDetails = new BallLogUserDetails(user);
        JwtResponseDto tokenDto = tokenProvider.generateToken(userDetails);

        JwtRefreshToken refreshToken = JwtRefreshToken.builder()
                .user(user)
                .refreshToken(tokenDto.getRefreshToken())
                .build();

        return tokenDto;
    }

    public JwtResponseDto reissueByRefreshToken(JwtRequestDto tokenRequestDto) {
        // 1. Refresh Token 검증
        if (!tokenProvider.validateToken(tokenRequestDto.getRefreshToken())) {
            throw new AuthorizationFailedException(ErrorCode.INVALID_REFRESH_TOKEN);
        }

        // 2. Access Token 에서 UserId 가져오기
        Authentication authentication = tokenProvider.getAuthentication(tokenRequestDto.getAccessToken());
        Long userId = Long.parseLong(authentication.getName());

        JwtRefreshToken jwt = refreshTokenRepository.findFirstByUserId(userId)
                .orElseThrow(() -> new AuthorizationFailedException(ErrorCode.INVALID_REFRESH_TOKEN));


        refreshTokenRepository.delete(jwt);
        refreshTokenRepository.flush();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("회원을 찾을 수 없습니다."));
        BallLogUserDetails userDetails = new BallLogUserDetails(user);

        JwtResponseDto tokenDto = tokenProvider.generateToken(userDetails);
        JwtRefreshToken newJwt = JwtRefreshToken.builder()
                .user(user)
                .refreshToken(tokenDto.getRefreshToken())
                .build();

        refreshTokenRepository.save(newJwt);

        return tokenDto;
    }
}
