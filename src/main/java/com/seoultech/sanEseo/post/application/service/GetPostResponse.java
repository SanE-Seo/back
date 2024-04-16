package com.seoultech.sanEseo.post.application.service;

import com.seoultech.sanEseo.post.domain.Category;
import com.seoultech.sanEseo.post.domain.Coordinate;
import com.seoultech.sanEseo.post.domain.PostImage;
import org.springframework.util.Assert;

import java.util.List;

public record GetPostResponse(Long id, Category category, String title, String subTitle, String description, int level, String time,
                              Coordinate coordinate, List<PostImage> images) {

    public GetPostResponse {
        Assert.hasText(title, "제목은 필수입니다.");
        Assert.hasText(subTitle, "부제목은 필수입니다.");
        Assert.hasText(description, "코스설명은 필수입니다.");
        Assert.isTrue(0 < level && level < 4, "코스레벨은 1부터 3까지 가능합니다.");
        Assert.hasText(time, "소요시간은 필수입니다.");
        Assert.notNull(images, "이미지는 필수입니다.");
        Assert.notNull(coordinate, "좌표는 필수입니다.");
    }
}
