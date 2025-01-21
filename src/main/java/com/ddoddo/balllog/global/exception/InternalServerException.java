package com.ddoddo.balllog.global.exception;

public class InternalServerException extends BusinessException {

    public InternalServerException(ErrorCode errorCode) {
        super(errorCode);
    }
}
