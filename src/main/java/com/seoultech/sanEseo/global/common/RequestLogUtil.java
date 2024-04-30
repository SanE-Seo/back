package com.seoultech.sanEseo.global.common;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class RequestLogUtil {

    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static String getBody(RequestWrapper request) throws IOException {
        String method = request.getMethod().toUpperCase();
        String contentType = request.getContentType();
        if (contentType == null) {
            return null;
        }

        if (method.startsWith("P") && contentType.contains("application/json")) {
            ServletInputStream inputStream = request.getInputStream();
            String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
            return messageBody;
        } else {
            return null;
        }
    }
}