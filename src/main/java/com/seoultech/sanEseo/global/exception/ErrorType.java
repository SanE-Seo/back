package com.seoultech.sanEseo.global.exception;

import lombok.Getter;

@Getter
public enum ErrorType {
    INVALID_JWT("E001", 401),
    DUPLICATE_NAME("E002", 400),
    DUPLICATE_EMAIL("E003", 400),
    INVALID_REQUEST("E004", 400),
    OAUTH_ERROR("E005", 400),
    NOT_LOGINED_MEMBER("E006", 400),
    PASSWORD_NOT_MATCH("E007", 400),
    MEMBER_NOT_EXISTS("E008", 400),
    DUPLICATE_LIKES("E009", 400),
    MEMBER_NOT_LIKED("E010", 400),
    AUTHOR_MISMATCH("E011", 400),
    UNAUTHORIZED_ACCESS("E012", 401),
    ENTITY_NOT_FOUND("E404", 404),
    INVALID_INPUT_VALUE("E403", 403),
    INTERNAL_ERROR("E999", 500);

    private final String errorCode;
    private final int statusCode;

    ErrorType(String errorCode, int statusCode) {
        this.errorCode = errorCode;
        this.statusCode = statusCode;
    }
}