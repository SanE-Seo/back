package com.seoultech.sanEseo.post_district.application.service;

import com.seoultech.sanEseo.post.domain.PostImage;
import org.springframework.util.Assert;

import java.util.List;

public record GetPostDistrictResponse(List<PostImage> postImages, String title, String subTitle, String time, String likes, float distance, int level){

    public GetPostDistrictResponse {
        Assert.hasText(title, "제목은 필수입니다.");
        Assert.hasText(subTitle, "부제목은 필수입니다.");
        Assert.hasText(time, "소요시간은 필수입니다.");
        Assert.hasText(likes, "좋아요 수는 필수입니다.");
        Assert.isTrue(0 < distance, "거리는 0보다 커야합니다.");
        Assert.isTrue(0 < level && level < 4, "코스레벨은 1부터 3까지 가능합니다.");
        Assert.notNull(postImages, "이미지는 필수입니다.");

    }

}
