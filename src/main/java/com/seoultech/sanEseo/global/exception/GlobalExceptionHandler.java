package com.seoultech.sanEseo.global.exception;

import com.seoultech.sanEseo.global.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> handleBusinessException(BusinessException e) {
        return ApiResponse.fail(e.getErrorType(), e.getMessage());
    }

    // 기타 예외 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e) {
        return ApiResponse.fail(ErrorType.INTERNAL_ERROR, "내부 서버 오류가 발생했습니다.");
    }
}

