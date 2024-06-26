package com.seoultech.sanEseo.post.application.service;

import com.seoultech.sanEseo.post.domain.Category;
import com.seoultech.sanEseo.public_api.application.service.dto.GetCoordinateResponse;
import org.springframework.util.Assert;

public record GetPostResponse(
        Long id,
        Long authorId,
        String authorName,
        String authorProfileImageUrl,
        Category category,
        String title,
        String subTitle,
        String description,
        String level,
        String time,
        String distance,
        String courseDetail,
        String transportation,
        String districtName,
        GetCoordinateResponse geometry) {

    public GetPostResponse {
        Assert.hasText(title, "제목은 필수입니다.");
        Assert.hasText(subTitle, "부제목은 필수입니다.");

    }
}
