package com.seoultech.sanEseo.global.common;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class ErrorLog {

    private String requestMethod;
    private String requestUrl;

    private String requestIp;
    private String errorType;
    private String errorMessage;

    private int statusCode;
    private String requestBody;

    @Builder
    public ErrorLog(String requestMethod, String requestUrl,String  requestIp, String errorType,
                       String errorMessage, int statusCode, String requestBody) {
        this.requestMethod = requestMethod;
        this.requestUrl = requestUrl;
        this.requestIp = requestIp;
        this.errorType = errorType;
        this.errorMessage = errorMessage;
        this.statusCode = statusCode;
        this.requestBody = requestBody;
    }
}