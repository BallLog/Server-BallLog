package com.ddoddo.balllog.global.exception;

public class AuthorizationFailedException extends BusinessException {

    public AuthorizationFailedException(ErrorCode errorCode) {
        super(errorCode);
    }
}
