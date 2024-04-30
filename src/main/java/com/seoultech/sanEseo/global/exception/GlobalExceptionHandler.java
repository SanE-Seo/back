package com.seoultech.sanEseo.global.exception;

import com.seoultech.sanEseo.global.common.ErrorLoggerHelper;
import com.seoultech.sanEseo.global.common.RequestWrapper;
import com.seoultech.sanEseo.global.response.ApiResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final ErrorLoggerHelper errorLoggerHelper;

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> handleBusinessException(BusinessException e, RequestWrapper request) throws IOException {
        errorLoggerHelper.log(request, e.getErrorType(), e.getMessage());
        return ApiResponse.fail(e.getErrorType(), e.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException e, RequestWrapper request) throws IOException {
        errorLoggerHelper.log(request, ErrorType.INVALID_INPUT_VALUE, e.getMessage());
        return ApiResponse.fail(ErrorType.INVALID_INPUT_VALUE, "잘못된 값입니다.");
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException e, RequestWrapper request)  throws IOException {
        errorLoggerHelper.log(request, ErrorType.ENTITY_NOT_FOUND, e.getMessage());
        return ApiResponse.fail(ErrorType.ENTITY_NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleIllegalArgumentException(MethodArgumentNotValidException e, RequestWrapper request) throws IOException {
        errorLoggerHelper.log(request, ErrorType.INVALID_INPUT_VALUE, e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return ApiResponse.fail(ErrorType.INVALID_INPUT_VALUE, e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }

    // 기타 예외 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e, RequestWrapper request) throws IOException {
        errorLoggerHelper.log(request, ErrorType.INTERNAL_ERROR, e.getMessage());
        return ApiResponse.fail(ErrorType.INTERNAL_ERROR, "내부 서버 오류가 발생했습니다.");
    }
}

