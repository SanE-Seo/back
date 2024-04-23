package com.seoultech.sanEseo.global.response;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class SuccessResponse {

    private final boolean success = true;
    private final String message;
    private List<Object> data = new ArrayList<>();

    public SuccessResponse(String message) {
        this.message = message;
    }

    public SuccessResponse(String message, Object data) {
        this.message = message;
        if(data instanceof List<?>){ // 리스트 데이터
            this.data = (List<Object>) data;
        } else { // 단일 데이터
            this.data.add(data);
        }
    }
}