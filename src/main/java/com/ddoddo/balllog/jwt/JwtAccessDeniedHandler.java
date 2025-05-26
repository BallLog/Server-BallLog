package com.ddoddo.balllog.jwt;

import com.ddoddo.balllog.global.exception.ErrorCode;
import com.ddoddo.balllog.global.response.ErrorResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN); // 403

        ErrorResponseDto errorResponse = ErrorResponseDto.of(ErrorCode.ACCESS_DENIED, "접근 권한이 없습니다.");

        ObjectMapper objectMapper = new ObjectMapper();
        String result = objectMapper.writeValueAsString(errorResponse);

        response.getWriter().write(result);
    }
}