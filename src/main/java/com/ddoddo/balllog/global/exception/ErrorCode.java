package com.ddoddo.balllog.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Optional;
import java.util.function.Predicate;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // Success
    OK(HttpStatus.OK, "SUCCESS"),

    /**
     * Common Error
     */
    INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버에 오류가 발생했습니다."),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "지원하지 않는 HTTP Method 요청입니다."),
    API_NOT_FOUND(HttpStatus.NOT_FOUND, "요청한 API를 찾을 수 없습니다."),
    QUERY_PARAMETER_REQUIRED(HttpStatus.BAD_REQUEST, "쿼리 파라미터가 필요한 API입니다."),
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "입력값이 올바르지 않습니다."),


    /**
     * Authorization Error
     */
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "인증되지 않은 사용자입니다."),
    MALFORMED_JWT(HttpStatus.UNAUTHORIZED, "올바르지 않은 형식의 JWT 토큰입니다."),
    EXPIRED_JWT(HttpStatus.UNAUTHORIZED, "만료된 JWT 토큰입니다."),
    UNSUPPORTED_JWT(HttpStatus.UNAUTHORIZED, "지원하지 않는 JWT 토큰입니다."),
    ILLEGAL_JWT(HttpStatus.UNAUTHORIZED, "잘못된 JWT 토큰입니다."),
    INVALID_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED, "유효하지 않은 Refresh Token입니다."),
    AUTH_INFO_NOT_FOUND(HttpStatus.UNAUTHORIZED, "Security Context에 인증 정보가 없습니다."),

    /**
     * Kakao Error
     */
    KAKAO_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "카카오 서버 연동에 오류가 발생했습니다."),

    /**
     * Apple Error
     */
    APPLE_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "애플 서버 연동에 오류가 발생했습니다."),
    APPLE_FAILED_TO_GET_TOKEN(HttpStatus.INTERNAL_SERVER_ERROR, "애플 토큰을 가져오는데 실패했습니다."),
    APPLE_FAILED_TO_GET_PUBLIC_KEY(HttpStatus.INTERNAL_SERVER_ERROR, "애플 공개키를 가져오는데 실패했습니다."),
    APPLE_FAILED_TO_GET_INFO(HttpStatus.INTERNAL_SERVER_ERROR, "애플 계정 정보를 가져오는데 실패했습니다."),
    APPLE_FAILED_TO_GET_CLIENT_SECRET(HttpStatus.INTERNAL_SERVER_ERROR, "애플 client_secret을 가져오는데 실패했습니다."),
    APPLE_FAILED_TO_REVOKE_ACCOUNT(HttpStatus.INTERNAL_SERVER_ERROR, "애플 계정을 해지하는데 실패했습니다."),

    /**
     * User Error
     */
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "회원 정보를 찾을 수 없습니다."),
    INVALID_USER(HttpStatus.UNAUTHORIZED, "유효하지 않은 회원입니다."),
    SOCIAL_TYPE_NOT_FOUND(HttpStatus.NOT_FOUND, "소셜 로그인 유형을 다시 확인해주세요."),

    /**
     * BallLog Error
     */
    BALL_LOG_NOT_FOUND(HttpStatus.NOT_FOUND, "볼로그 정보를 찾을 수 없습니다."),

    /**
     * KBO Error
     */
    KBO_TEAM_NOT_FOUND(HttpStatus.NOT_FOUND, "구단 정보를 찾을 수 없습니다."),

    /**
     * Stadium Error
     */
    STADIUM_NOT_FOUND(HttpStatus.NOT_FOUND, "경기구장 정보를 찾을 수 없습니다."),

    /**
     * File Error
     */
    FILE_DELETE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "파일 삭제에 실패했습니다."),

    ;

    private final HttpStatus httpStatus;
    private final String message;

    public String getMessage(Throwable throwable) {
        return this.getMessage(this.getMessage(this.getMessage() + " - " + throwable.getMessage()));
    }

    public String getMessage(String message) {
        return Optional.ofNullable(message)
                .filter(Predicate.not(String::isBlank))
                .orElse(this.getMessage());
    }

}
