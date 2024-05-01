package com.seoultech.sanEseo.like.exception;

import com.seoultech.sanEseo.global.exception.BusinessException;
import com.seoultech.sanEseo.global.exception.ErrorType;

public class MemberNotLikedException extends BusinessException {

    public MemberNotLikedException(String message) {
        super(ErrorType.MEMBER_NOT_LIKED, message);
    }
}
