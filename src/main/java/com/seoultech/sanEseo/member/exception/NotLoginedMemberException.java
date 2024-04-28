package com.seoultech.sanEseo.member.exception;

import com.seoultech.sanEseo.global.exception.BusinessException;
import com.seoultech.sanEseo.global.exception.ErrorType;

public class NotLoginedMemberException extends BusinessException {

    public NotLoginedMemberException(String message) {
        super(ErrorType.NOT_LOGINED_MEMBER, message);
    }
}
