package com.seoultech.sanEseo.global.exception;

import lombok.Getter;

@Getter
public enum ErrorType {
    INVALID_JWT("E001", 401),
    DUPLICATE_NAME("E002", 400),
    DUPLICATE_EMAIL("E003", 400),
    INVALID_REQUEST("E004", 400),
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