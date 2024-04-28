package com.seoultech.sanEseo.member.exception;

import com.seoultech.sanEseo.global.exception.BusinessException;
import com.seoultech.sanEseo.global.exception.ErrorType;

public class DuplicateNameException extends BusinessException {

    public DuplicateNameException(String message) {
        super(ErrorType.DUPLICATE_NAME, message);
    }
}
