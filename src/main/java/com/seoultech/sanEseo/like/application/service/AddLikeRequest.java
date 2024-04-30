package com.seoultech.sanEseo.like.application.service;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class AddLikeRequest {
    @NotNull(message = "포스트 ID는 필수입니다.")
    private Long postId;

    // 모든 매개변수를 가진 생성자
    public AddLikeRequest(Long postId) {
        this.postId = postId;
    }
}