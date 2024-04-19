package com.seoultech.sanEseo.review;

import java.util.List;

public interface ReviewPort {
    void createReview(Review review);

    void deleteReview(Long id);

    void updateReview(Long id, Review review);

    List<GetReviewResponse> getReviewList(Long postId);
}
