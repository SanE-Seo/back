package com.seoultech.sanEseo.post_district.application.service;

import com.seoultech.sanEseo.post.domain.PostImage;
import org.springframework.util.Assert;

import java.util.List;

public record GetPostDistrictResponse(List<PostImage> postImages, String title, String subTitle, String time, String likes, String distance, String courseDetail, String transportation, String level){

    public GetPostDistrictResponse {
        Assert.hasText(title, "제목은 필수입니다.");
        Assert.hasText(subTitle, "부제목은 필수입니다.");
        Assert.hasText(time, "소요시간은 필수입니다.");
        Assert.hasText(likes, "좋아요 수는 필수입니다.");
        Assert.hasText(distance, "거리는 필수입니다.");
        Assert.hasText(courseDetail, "코스 상세는 필수입니다.");
        Assert.hasText(transportation, "교통수단은 필수입니다.");
        Assert.hasText(level, "난이도는 필수입니다.");
        Assert.notNull(postImages, "이미지는 필수입니다.");

    }

}
