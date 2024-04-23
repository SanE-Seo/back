package com.seoultech.sanEseo.global.response;

import com.seoultech.sanEseo.global.exception.ErrorType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiResponse {
    public static ResponseEntity<?> ok(String message){
        SuccessResponse response = new SuccessResponse(message);
        return ResponseEntity.ok(response);
    }

    public static ResponseEntity<?> ok(String message, Object data){
        SuccessResponse response = new SuccessResponse(message, data);
        return ResponseEntity.ok(response);
    }

    public static ResponseEntity<?> fail(ErrorType errorType, String message){
        FailResponse response = new FailResponse(message, errorType.getErrorCode());
        return ResponseEntity.status(errorType.getStatusCode())
                .body(response);
    }
}
