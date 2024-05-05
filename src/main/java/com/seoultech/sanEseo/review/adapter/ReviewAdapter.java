package com.seoultech.sanEseo.review.adapter;

import com.seoultech.sanEseo.member.domain.Member;
import com.seoultech.sanEseo.post.domain.Post;
import com.seoultech.sanEseo.review.application.port.ReviewPort;
import com.seoultech.sanEseo.review.application.service.GetReviewResponse;
import com.seoultech.sanEseo.review.domain.Review;
import jakarta.persistence.EntityNotFoundException;
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
    public void deleteReview(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }

    @Override
    public List<GetReviewResponse> getReviewList(Long postId) {

        return reviewRepository.findByPostId(postId).stream()
                .map(GetReviewResponse::fromEntity)
                .toList();
    }

    @Override
    public Review findById(Long reviewId) {
        return reviewRepository.findById(reviewId).orElseThrow(
                () -> new EntityNotFoundException("해당 리뷰가 존재하지 않습니다.")
        );
    }
}
