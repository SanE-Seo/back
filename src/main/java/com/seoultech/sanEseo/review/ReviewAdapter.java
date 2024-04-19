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

    @Override
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }

    @Override
    public void updateReview(Long id, Review review) {
        reviewRepository.save(review);
    }
}
