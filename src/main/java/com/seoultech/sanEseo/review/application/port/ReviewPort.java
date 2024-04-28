package com.seoultech.sanEseo.review.application.port;

import com.seoultech.sanEseo.member.domain.Member;
import com.seoultech.sanEseo.post.domain.Post;
import com.seoultech.sanEseo.review.application.service.GetReviewResponse;
import com.seoultech.sanEseo.review.domain.Review;

import java.util.List;

public interface ReviewPort {
    void createReview(Review review);

    void deleteReview(Post post, Member member);

    void updateReview(Post post, Member member, Review review);

    List<GetReviewResponse> getReviewList(Long postId);
}
