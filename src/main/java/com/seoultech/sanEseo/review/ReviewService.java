package com.seoultech.sanEseo.review;

import com.seoultech.sanEseo.member.application.port.out.MemberPort;
import com.seoultech.sanEseo.member.domain.Member;
import com.seoultech.sanEseo.post.application.port.PostPort;
import com.seoultech.sanEseo.post.domain.Post;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class ReviewService {
    private ReviewPort reviewPort;
    private MemberPort memberPort;
    private PostPort postPort;

    ReviewService(final ReviewPort reviewPort, final MemberPort memberPort, final PostPort postPort) {
        this.reviewPort = reviewPort;
        this.memberPort = memberPort;
        this.postPort = postPort;
    }

    public void createReview(CreateReviewRequest request) {

        Member member = memberPort.loadById(request.memberId());
        Post post = postPort.getPost(request.postId());

        Review review = Review.builder()
                .member(member)
                .post(post)
                .content(request.content())
                .build();
        reviewPort.createReview(review);
    }

    public void deleteReview(Long id) {
        reviewPort.deleteReview(id);
    }

    public void updateReview(Long id, CreateReviewRequest request) {
        Member member = memberPort.loadById(request.memberId());
        Post post = postPort.getPost(request.postId());

        Review review = Review.builder()
                .member(member)
                .post(post)
                .content(request.content())
                .build();
        reviewPort.updateReview(id, review);
    }

    public List<GetReviewResponse> getReviewList(Long postId) {

        return reviewPort.getReviewList(postId);
    }
}
