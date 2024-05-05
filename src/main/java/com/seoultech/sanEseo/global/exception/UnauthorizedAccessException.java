package com.seoultech.sanEseo.global.exception;

public class UnauthorizedAccessException extends BusinessException{

    public UnauthorizedAccessException(String message) {
        super(ErrorType.UNAUTHORIZED_ACCESS, message);
    }
}

