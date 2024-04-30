package com.seoultech.sanEseo.global.common;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AccessLog {

    private String requestMethod;

    private String requestUrl;

    private String requestIp;

    private String requestBody;

    @Builder
    public AccessLog(String requestMethod, String requestUrl, String requestIp, String requestBody) {
        this.requestMethod = requestMethod;
        this.requestUrl = requestUrl;
        this.requestIp = requestIp;
        this.requestBody = requestBody;
    }

}
