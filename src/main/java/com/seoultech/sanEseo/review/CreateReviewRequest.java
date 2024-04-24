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
    Long postId;
    String content;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime createDate;

    public CreateReviewRequest(Long memberId, Long postId, String content, LocalDateTime createDate) {
        this.memberId = memberId;
        this.postId = postId;
        this.content = content;
        this.createDate = createDate;
    }
}
