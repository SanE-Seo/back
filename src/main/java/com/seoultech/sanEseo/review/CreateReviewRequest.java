package com.seoultech.sanEseo.review;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

@Getter
public class CreateReviewRequest {

    @NotNull(message = "사용자 ID는 필수입니다.")
    Long memberId;
    @NotNull(message = "게시글 ID는 필수입니다.")
    Long postId;
    @NotBlank(message = "리뷰 내용은 필수입니다.")
    String content;
    @NotNull(message = "리뷰 작성일은 필수입니다.")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime createDate;

    public CreateReviewRequest(Long memberId, Long postId, String content, LocalDateTime createDate) {
        this.memberId = memberId;
        this.postId = postId;
        this.content = content;
        this.createDate = createDate;
    }
}
