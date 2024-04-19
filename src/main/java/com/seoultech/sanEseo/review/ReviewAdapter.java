package com.seoultech.sanEseo.review;

import org.springframework.stereotype.Component;

@Component
class ReviewAdapter implements ReviewPort {

    private final ReviewRepository reviewRepository;

    private ReviewAdapter(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public void createReview(Review review) {

        reviewRepository.save(review);
    }
}
