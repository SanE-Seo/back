package com.seoultech.sanEseo.review.application.service;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.seoultech.sanEseo.member.domain.Member;
import com.seoultech.sanEseo.review.domain.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetReviewResponse {
    Long reviewId;
    Long authorId;
    String authorName;
    String authorProfileImageUrl;
    Long postId;
    String content;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    LocalDateTime createAt;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    LocalDateTime updateAt;

    public static GetReviewResponse fromEntity(Review review) {
        Member author = review.getMember();
        return GetReviewResponse.builder()
                .reviewId(review.getId())
                .authorId(author.getId())
                .authorName(author.getName())
                .authorProfileImageUrl(author.getProfile())
                .postId(review.getPost().getId())
                .content(review.getContent())
                .createAt(review.getCreateAt())
                .updateAt(review.getUpdateAt())
                .build();
    }
}