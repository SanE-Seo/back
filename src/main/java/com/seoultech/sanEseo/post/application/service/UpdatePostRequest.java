package com.seoultech.sanEseo.post.application.service;

import com.seoultech.sanEseo.post.domain.Category;
import com.seoultech.sanEseo.post.domain.Coordinate;
import com.seoultech.sanEseo.post.domain.PostImage;

public record UpdatePostRequest(
        Category category,
        String title,
        String subTitle,
        String description,
        int level,
        String time,
        Coordinate coordinate,
        Iterable<PostImage> images) {

}
