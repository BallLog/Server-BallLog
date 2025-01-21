package com.ddoddo.balllog.global.exception;

public class UnsupportedException extends BusinessException {

    public UnsupportedException(ErrorCode errorCode) {
        super(errorCode);
    }
}