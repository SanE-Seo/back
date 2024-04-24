package com.seoultech.sanEseo.global.response;

import com.seoultech.sanEseo.global.exception.ErrorType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collection;

public class ApiResponse {
    public static ResponseEntity<?> ok(String message){
        SuccessResponse response = new SuccessResponse(message);
        return ResponseEntity.ok(response);
    }

    public static ResponseEntity<?> ok(String message, Object data){
        if (data instanceof Collection && ((Collection<?>) data).isEmpty()) {
            return noContent(message); // 데이터가 없을 때 적절한 응답 반환
        }
        SuccessResponse response = new SuccessResponse(message, data);
        return ResponseEntity.ok(response);
    }

    public static ResponseEntity<?> noContent(String message) {
        // HTTP 204 No Content 상태와 함께 메시지 반환
        return ResponseEntity.noContent().header("X-Message", message).build();
    }

    public static ResponseEntity<?> fail(ErrorType errorType, String message){
        FailResponse response = new FailResponse(message, errorType.getErrorCode());
        return ResponseEntity.status(errorType.getStatusCode()).body(response);
    }
}

