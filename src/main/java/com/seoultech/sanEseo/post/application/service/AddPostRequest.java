package com.seoultech.sanEseo.post.application.service;

import com.seoultech.sanEseo.post.domain.Coordinate;
import com.seoultech.sanEseo.post.domain.Category;
import com.seoultech.sanEseo.post.domain.PostImage;
import org.springframework.util.Assert;

import java.util.List;

public record AddPostRequest(
        Category category,
        String title,
        String subTitle,
        String description,
        String level,
        String time,
        String distance,
        String courseDetail,
        String transportation,
        Coordinate coordinate,
        List<PostImage> images,
        List<Long> districtIds  // District 식별자 추가
) {
    public AddPostRequest {
        Assert.hasText(title, "제목은 필수입니다.");
        Assert.hasText(subTitle, "부제목은 필수입니다.");
        Assert.hasText(description, "코스설명은 필수입니다.");
        Assert.hasText(level, "난이도는 필수입니다.");
        Assert.hasText(time, "소요시간은 필수입니다.");
        Assert.notNull(images, "이미지는 필수입니다.");
        Assert.notNull(distance, "거리는 0보다 커야합니다.");
        Assert.notNull(courseDetail, "코스 상세는 필수입니다.");
        Assert.notNull(transportation, "교통수단은 필수입니다.");
        Assert.notNull(coordinate, "좌표는 필수입니다.");
        Assert.notNull(category, "카테고리는 필수입니다.");
        Assert.notNull(districtIds, "자치구 ID는 필수입니다.");
    }
}