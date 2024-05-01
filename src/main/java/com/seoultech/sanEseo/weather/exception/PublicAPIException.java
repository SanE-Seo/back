package com.seoultech.sanEseo.weather.exception;

import com.seoultech.sanEseo.global.exception.BusinessException;
import com.seoultech.sanEseo.global.exception.ErrorType;

public class PublicAPIException extends BusinessException {

    public PublicAPIException(String message) {
        super(ErrorType.INTERNAL_ERROR, message);
    }
}
