package com.seoultech.sanEseo.review.application.service;

import com.seoultech.sanEseo.global.exception.UnauthorizedAccessException;
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
    public void createReview(Long memberId, Long postId, CreateReviewRequest request) {

        Post post = postPort.getPost(postId);
        Member member = memberPort.loadById(memberId);

        Review review = Review.builder()
                .member(member)
                .post(post)
                .content(request.getContent())
                .build();

        reviewPort.createReview(review);
    }

    @Transactional
    public void deleteReview(Long memberId, Long postId, Long reviewId) {
        Post post = postPort.getPost(postId);
        Review review = reviewPort.findById(reviewId);

        if(!post.getId().equals(review.getPost().getId())) {
            throw new IllegalArgumentException("해당 게시글에 존재하지 않는 리뷰입니다.");
        }

        if(!review.getMember().getId().equals(memberId)) {
            throw new UnauthorizedAccessException("해당 리뷰를 삭제할 권한이 없습니다.");
        }

        reviewPort.deleteReview(reviewId);
    }

    @Transactional
    public void updateReview(Long memberId, Long postId, Long reviewId, UpdateReviewRequest request) {
        Post post = postPort.getPost(postId);
        Review review = reviewPort.findById(reviewId);

        if(!post.getId().equals(review.getPost().getId())) {
            throw new IllegalArgumentException("해당 게시글에 존재하지 않는 리뷰입니다.");
        }

        if(!review.getMember().getId().equals(memberId)) {
            throw new UnauthorizedAccessException("해당 리뷰를 수정할 권한이 없습니다.");
        }

        review.updateContent(request.getContent());
    }

    public List<GetReviewResponse> getReviewList(Long postId) {

        return reviewPort.getReviewList(postId);
    }
}
