package com.seoultech.sanEseo.review.adapter;

import com.seoultech.sanEseo.member.domain.Member;
import com.seoultech.sanEseo.post.domain.Post;
import com.seoultech.sanEseo.review.application.port.ReviewPort;
import com.seoultech.sanEseo.review.application.service.GetReviewResponse;
import com.seoultech.sanEseo.review.domain.Review;
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
    public void deleteReview(Post post, Member member) {
        reviewRepository.deleteByPostAndMember(post, member);
    }

    @Override
    public void updateReview(Post post, Member member, Review review) {
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
