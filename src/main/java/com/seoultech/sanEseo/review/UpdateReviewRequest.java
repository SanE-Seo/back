package com.seoultech.sanEseo.review;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

@Getter
public class UpdateReviewRequest {
        @NotBlank(message = "리뷰 내용은 필수입니다.")
        String content;

        @NotNull(message = "리뷰 작성일은 필수입니다.")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime createDate;

        public UpdateReviewRequest() {
            this.content= content;
            this.createDate = createDate;
        }
}