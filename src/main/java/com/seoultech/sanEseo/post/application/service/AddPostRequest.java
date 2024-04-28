package com.seoultech.sanEseo.post.application.service;

import com.seoultech.sanEseo.post.domain.Coordinate;
import com.seoultech.sanEseo.post.domain.Category;
import com.seoultech.sanEseo.post.domain.PostImage;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.util.Assert;

import java.util.List;

@Getter
public class AddPostRequest {
    @NotNull(message = "카테고리는 필수입니다.")
    private Category category;

    @NotBlank(message = "제목은 필수입니다.")
    private String title;

    @NotBlank(message = "부제목은 필수입니다.")
    private String subTitle;

    @NotBlank(message = "코스설명은 필수입니다.")
    private String description;

    @NotBlank(message = "난이도는 필수입니다.")
    private String level;

    @NotBlank(message = "소요시간은 필수입니다.")
    private String time;

    @NotBlank(message = "거리는 0보다 커야합니다.")
    private String distance;

    @NotBlank(message = "코스 상세는 필수입니다.")
    private String courseDetail;

    @NotBlank(message = "교통수단은 필수입니다.")
    private String transportation;

    @NotNull(message = "이미지는 필수입니다.")
    private List<PostImage> images;

    @NotNull(message = "자치구 ID는 필수입니다.")
    private Long districtId;

    // 생성자, 게터, 세터 등 추가 필요

    public AddPostRequest(Category category, String title, String subTitle, String description, String level, String time, String distance, String courseDetail, String transportation, List<PostImage> images, Long districtId) {
        this.category = category;
        this.title = title;
        this.subTitle = subTitle;
        this.description = description;
        this.level = level;
        this.time = time;
        this.distance = distance;
        this.courseDetail = courseDetail;
        this.transportation = transportation;
        this.images = images;
        this.districtId = districtId;
    }
}