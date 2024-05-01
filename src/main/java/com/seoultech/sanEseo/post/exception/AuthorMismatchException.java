package com.seoultech.sanEseo.post.exception;

import com.seoultech.sanEseo.global.exception.BusinessException;
import com.seoultech.sanEseo.global.exception.ErrorType;

public class AuthorMismatchException extends BusinessException {

    public AuthorMismatchException(String message) {
        super(ErrorType.AUTHOR_MISMATCH, message);
    }
}
