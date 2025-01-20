package com.ddoddo.balllog.global.exception;

public class AppleServerException extends InternalServerException {

    public AppleServerException() {
        super(ErrorCode.APPLE_SERVER_ERROR);
    }

    public AppleServerException(ErrorCode errorCode) {
        super(errorCode);
    }
}
