package com.seoultech.sanEseo.member.exception;

import com.seoultech.sanEseo.global.exception.BusinessException;
import com.seoultech.sanEseo.global.exception.ErrorType;

public class OAuthException extends BusinessException {

    public OAuthException(String message) {
        super(ErrorType.OAUTH_ERROR, message);
    }
}
