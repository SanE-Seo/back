package com.seoultech.sanEseo.post.application.service;

import com.seoultech.sanEseo.post.domain.Category;
import com.seoultech.sanEseo.post.domain.Coordinate;
import com.seoultech.sanEseo.post.domain.PostImage;

import java.util.List;

public record UpdatePostRequest(
        Category category,
        String title,
        String subTitle,
        String description,
        int level,
        String time,
        float distance,
        Coordinate coordinate,
        Iterable<PostImage> images,
        List<Long> districtIds) { // 자료형과 변수명 변경

}
