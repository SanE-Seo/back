package com.seoultech.sanEseo.global.response;

import lombok.Getter;

@Getter
public class FailResponse {

    private final boolean success = false;
    private final String message;
    private final String errorCode;

    public FailResponse(String message, String errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }
}