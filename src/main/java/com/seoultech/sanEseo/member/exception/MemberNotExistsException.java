package com.seoultech.sanEseo.member.exception;

import com.seoultech.sanEseo.global.exception.BusinessException;
import com.seoultech.sanEseo.global.exception.ErrorType;

public class MemberNotExistsException extends BusinessException {

    public MemberNotExistsException(String message) {
        super(ErrorType.PASSWORD_NOT_MATCH, message);
    }
}
