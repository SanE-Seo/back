package com.seoultech.sanEseo.review.application.service;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class CreateReviewRequest {

    @NotBlank(message = "리뷰 내용은 필수 입력 값입니다.")
    String content;

    public CreateReviewRequest(String content) {
        this.content = content;
    }

}
