package com.seoultech.sanEseo.post_district.application.service;

import com.seoultech.sanEseo.image.GetImageResponse;
import com.seoultech.sanEseo.image.PostImage;
import org.springframework.util.Assert;

import java.util.List;

public record GetPostDistrictResponse(Long postId, Long authorId, String authorName, String authorProfileImageUrl, List<GetImageResponse> postImages, String title, String subTitle, String time, int likes, String distance, String level, String districts) {

    public GetPostDistrictResponse {
        Assert.notNull(postId, "게시글 ID는 필수입니다.");
        Assert.hasText(title, "제목은 필수입니다.");
        Assert.hasText(subTitle, "부제목은 필수입니다.");
        Assert.hasText(time, "소요시간은 필수입니다.");
        Assert.notNull(likes, "좋아요 수는 필수입니다.");
        Assert.hasText(distance, "거리는 필수입니다.");
        Assert.hasText(level, "난이도는 필수입니다.");
        Assert.notNull(postImages, "이미지는 필수입니다.");
        Assert.notNull(districts, "지역은 필수입니다.");
    }

}
