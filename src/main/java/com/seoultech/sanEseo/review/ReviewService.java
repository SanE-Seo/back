package com.seoultech.sanEseo.review;

import com.seoultech.sanEseo.member.application.port.out.MemberPort;
import com.seoultech.sanEseo.member.domain.Member;
import com.seoultech.sanEseo.post.application.port.PostPort;
import com.seoultech.sanEseo.post.domain.Post;

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

        Review review = new Review(member, post, request.content(), request.createDate());
        reviewPort.createReview(review);
    }
}
