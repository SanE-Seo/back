package com.seoultech.sanEseo.post.application.service;

import com.seoultech.sanEseo.post.domain.Category;
import org.springframework.util.Assert;

public record GetPostResponse(Long id, Category category, String title, String subTitle, String description, String level, String time,
                              String distance, String courseDetail, String transportation, String districtName) {

    public GetPostResponse {
        Assert.hasText(title, "제목은 필수입니다.");
        Assert.hasText(subTitle, "부제목은 필수입니다.");
        Assert.hasText(description, "코스설명은 필수입니다.");
        Assert.hasText(level, "난이도는 필수입니다.");
        Assert.hasText(time, "소요시간은 필수입니다.");
        Assert.hasText(distance, "거리는 필수입니다.");
        Assert.hasText(courseDetail, "코스 상세는 필수입니다.");
        Assert.hasText(transportation, "교통수단은 필수입니다.");
        Assert.notNull(districtName, "자치구는 필수입니다.");
    }
}
