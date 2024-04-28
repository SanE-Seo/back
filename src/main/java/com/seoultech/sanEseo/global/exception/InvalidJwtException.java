package com.seoultech.sanEseo.global.exception;

public class InvalidJwtException extends BusinessException{

    public InvalidJwtException( String message) {
        super(ErrorType.INVALID_JWT, message);
    }
}

