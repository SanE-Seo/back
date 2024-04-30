package com.seoultech.sanEseo.global.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seoultech.sanEseo.global.exception.ErrorType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class ErrorLoggerHelper {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void log(RequestWrapper request, ErrorType errorType, String mesasge)
            throws IOException {
        ErrorLog errorLogDto = toErrorLog(request, errorType, mesasge);
        log.error(objectMapper.writeValueAsString(errorLogDto));
    }

    private ErrorLog toErrorLog(RequestWrapper request, ErrorType errorType,
                                   String message)
            throws IOException {
        return ErrorLog.builder()
                .requestMethod(request.getMethod())
                .requestUrl(request.getRequestURI())
                .requestIp(RequestLogUtil.getIp(request))
                .errorMessage(message)
                .errorType(errorType.getErrorCode())
                .statusCode(errorType.getStatusCode())
                .requestBody(RequestLogUtil.getBody(request))
                .build();
    }
}

