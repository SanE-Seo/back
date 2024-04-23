package com.seoultech.sanEseo.member.exception;

import com.seoultech.sanEseo.global.exception.BusinessException;
import com.seoultech.sanEseo.global.exception.ErrorType;

public class DuplicateEmailException extends BusinessException {

    public DuplicateEmailException(String message) {
        super(ErrorType.DUPLICATE_EMAIL, message);
    }
}
