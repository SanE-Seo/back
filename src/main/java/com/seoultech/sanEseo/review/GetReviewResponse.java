package com.seoultech.sanEseo.review;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

public record GetReviewResponse(
        Long reviewId,
        Long memberId,
        Long postId,
        String content,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime createDate
) {
    @Builder
    public GetReviewResponse {
        Assert.notNull(reviewId, "리뷰 ID는 필수입니다.");
        Assert.notNull(memberId, "사용자 ID는 필수입니다.");
        Assert.notNull(postId, "게시글 ID는 필수입니다.");
        Assert.hasText(content, "리뷰 내용은 필수입니다.");
        Assert.notNull(createDate, "리뷰 작성일은 필수입니다.");
    }
}