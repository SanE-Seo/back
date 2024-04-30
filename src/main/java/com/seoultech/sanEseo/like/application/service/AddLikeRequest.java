package com.seoultech.sanEseo.like.application.service;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddLikeRequest {
    @NotNull(message = "포스트 ID는 필수입니다.")
    private Long postId;
}