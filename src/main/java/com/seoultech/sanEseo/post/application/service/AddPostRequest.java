package com.seoultech.sanEseo.post.application.service;

import com.seoultech.sanEseo.post.domain.Category;
import org.springframework.util.Assert;

public record AddPostRequest(Category category, String title, String subTitle, String description, int level,
                             String time, String image) {
    public AddPostRequest {
        Assert.hasText(title, "제목은 필수입니다.");
        Assert.hasText(subTitle, "부제목은 필수입니다.");
        Assert.hasText(description, "코스설명은 필수입니다.");
        Assert.isTrue(0 < level && level < 4, "코스레벨은 1부터 3까지 가능합니다.");
        Assert.hasText(time, "소요시간은 필수입니다.");
        Assert.hasText(image, "코스이미지는 필수입니다.");
        Assert.notNull(category, "카테고리는 필수입니다.");
    }
}
