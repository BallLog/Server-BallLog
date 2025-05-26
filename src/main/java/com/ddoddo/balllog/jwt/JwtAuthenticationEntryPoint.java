package com.ddoddo.balllog.jwt;

import com.ddoddo.balllog.global.exception.ErrorCode;
import com.ddoddo.balllog.global.response.ErrorResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws ServletException, IOException {
        // 유저 정보 없이 접근할 경우 401
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401

        ErrorResponseDto errorResponse = ErrorResponseDto.of(ErrorCode.UNAUTHORIZED, "JWT 토큰이 없습니다.");

        ObjectMapper objectMapper = new ObjectMapper();
        String result = objectMapper.writeValueAsString(errorResponse);

        response.getWriter().write(result);
    }
}