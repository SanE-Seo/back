package com.seoultech.sanEseo.review;

import java.util.HashMap;
import java.util.Map;

class ReviewRepository {

    private final Map<Long, Review> persistence = new HashMap<>();
    private Long sequence = 0L;

    public void save(Review review) {
        review.assignId(++sequence);
        persistence.put(review.getId(), review);
    }
}
