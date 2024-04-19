package com.seoultech.sanEseo.review;

import org.springframework.stereotype.Component;

import java.util.List;

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

    @Override
    public List<GetReviewResponse> getReviewList(Long postId) {

        List<GetReviewResponse> reviewResponses = reviewRepository.findByPostId(postId).stream()
                .map(review -> GetReviewResponse.builder()
                        .reviewId(review.getId())
                        .memberId(review.getMember().getId())
                        .postId(review.getPost().getId())
                        .content(review.getContent())
                        .createDate(review.getCreateDate())
                        .build())
                .toList();

        return reviewResponses;
    }
}
