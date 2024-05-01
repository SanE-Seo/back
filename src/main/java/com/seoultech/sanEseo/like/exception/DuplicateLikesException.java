package com.seoultech.sanEseo.like.exception;

import com.seoultech.sanEseo.global.exception.BusinessException;
import com.seoultech.sanEseo.global.exception.ErrorType;

public class DuplicateLikesException extends BusinessException {

    public DuplicateLikesException(String message) {
        super(ErrorType.DUPLICATE_LIKES, message);
    }
}
