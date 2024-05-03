package com.seoultech.sanEseo.post.application.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.seoultech.sanEseo.post.domain.Category;
import com.seoultech.sanEseo.public_api.application.service.dto.CoordinateRequest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

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

    @NotNull(message = "자치구 ID는 필수입니다.")
    private Long districtId;

    @NotNull(message = "좌표 정보는 필수입니다.")
    private CoordinateRequest geometry;

    // 생성자, 게터, 세터 등 추가 필요

    // 생성자에 @JsonProperty 어노테이션 추가
    public AddPostRequest(@JsonProperty("category") Category category,
                          @JsonProperty("title") String title,
                          @JsonProperty("subTitle") String subTitle,
                          @JsonProperty("description") String description,
                          @JsonProperty("level") String level,
                          @JsonProperty("time") String time,
                          @JsonProperty("distance") String distance,
                          @JsonProperty("courseDetail") String courseDetail,
                          @JsonProperty("transportation") String transportation,
                          @JsonProperty("districtId") Long districtId,
                          @JsonProperty("geometry") CoordinateRequest geometry) {
        this.category = category;
        this.title = title;
        this.subTitle = subTitle;
        this.description = description;
        this.level = level;
        this.time = time;
        this.distance = distance;
        this.courseDetail = courseDetail;
        this.transportation = transportation;
        this.districtId = districtId;
        this.geometry = geometry;
    }




}