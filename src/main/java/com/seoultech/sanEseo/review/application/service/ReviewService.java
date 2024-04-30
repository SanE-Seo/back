package com.seoultech.sanEseo.review.application.service;

import com.seoultech.sanEseo.member.application.port.out.MemberPort;
import com.seoultech.sanEseo.member.domain.Member;
import com.seoultech.sanEseo.post.application.port.PostPort;
import com.seoultech.sanEseo.post.domain.Post;
import com.seoultech.sanEseo.review.domain.Review;
import com.seoultech.sanEseo.review.application.port.ReviewPort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public
class ReviewService {
    private ReviewPort reviewPort;
    private MemberPort memberPort;
    private PostPort postPort;

    ReviewService(final ReviewPort reviewPort, final MemberPort memberPort, final PostPort postPort) {
        this.reviewPort = reviewPort;
        this.memberPort = memberPort;
        this.postPort = postPort;
    }

    @Transactional
    public void createReview(Long memberId, CreateReviewRequest request) {

        Member member = memberPort.loadById(memberId);
        Post post = postPort.getPost(request.getPostId());

        Review review = Review.builder()
                .member(member)
                .post(post)
                .content(request.getContent())
                .createDate(request.getCreateDate())
                .build();
        reviewPort.createReview(review);
    }

    @Transactional
    public void deleteReview(Long postId, Long memberId) {
        Member member = memberPort.loadById(memberId);
        Post post = postPort.getPost(postId);

        reviewPort.deleteReview(post, member);
    }

    @Transactional
    public void updateReview(Long postId, Long memberId, UpdateReviewRequest request) {
        Member member = memberPort.loadById(memberId);
        Post post = postPort.getPost(postId);

        Review review = Review.builder()
                .member(member)
                .post(post)
                .content(request.getContent())
                .createDate(request.getCreateDate())
                .build();
        reviewPort.updateReview(post, member, review);
    }

    public List<GetReviewResponse> getReviewList(Long postId) {

        return reviewPort.getReviewList(postId);
    }
}
