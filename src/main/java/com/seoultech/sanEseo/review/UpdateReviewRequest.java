package com.seoultech.sanEseo.review;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

public record UpdateReviewRequest(
        String content,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime createDate
) {
    public UpdateReviewRequest {
        Assert.hasText(content, "리뷰 내용은 필수입니다.");
        Assert.notNull(createDate, "리뷰 작성일은 필수입니다.");
    }
}