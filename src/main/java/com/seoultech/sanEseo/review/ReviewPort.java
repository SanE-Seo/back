package com.seoultech.sanEseo.review;

public interface ReviewPort {
    void createReview(Review review);

    void deleteReview(Long id);
}
