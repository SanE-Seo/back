package com.seoultech.sanEseo.global.exception;

import com.seoultech.sanEseo.global.response.ApiResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> handleBusinessException(BusinessException e) {
        return ApiResponse.fail(e.getErrorType(), e.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException e) {
        return ApiResponse.fail(ErrorType.ENTITY_NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleIllegalArgumentException(MethodArgumentNotValidException e) {
        // 입력값 오류에 대한 응답 처리
        return ApiResponse.fail(ErrorType.INVALID_INPUT_VALUE, e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }

    // 기타 예외 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e) {
        e.printStackTrace(); // 혹은 Logger 사용
        return ApiResponse.fail(ErrorType.INTERNAL_ERROR, "내부 서버 오류가 발생했습니다.");
    }
}

