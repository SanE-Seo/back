package com.seoultech.sanEseo.review;

import com.seoultech.sanEseo.member.domain.Member;
import com.seoultech.sanEseo.post.domain.Post;

import java.util.List;

public interface ReviewPort {
    void createReview(Review review);

    void deleteReview(Post post, Member member);

    void updateReview(Post post, Member member, Review review);

    List<GetReviewResponse> getReviewList(Long postId);
}
