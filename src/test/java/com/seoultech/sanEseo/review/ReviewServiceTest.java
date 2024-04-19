package com.seoultech.sanEseo.review;

import com.seoultech.sanEseo.member.application.port.out.MemberPort;
import com.seoultech.sanEseo.post.application.port.PostPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;

public class ReviewServiceTest {

    private ReviewService reviewService;
    private ReviewPort reviewPort;
    private MemberPort memberPort;
    private PostPort postPort;
    private ReviewRepository reviewRepository;


    @BeforeEach
    void setUp() {
        reviewRepository = new ReviewRepository();
        reviewPort = Mockito.mock(ReviewPort.class);
        memberPort = Mockito.mock(MemberPort.class);
        postPort = Mockito.mock(PostPort.class);
        reviewService = new ReviewService(reviewPort, memberPort, postPort);
    }

    @Test
    void 리뷰등록() {

        Long memberId = 1L;
        Long postId = 1L;
        String content = "리뷰 내용";
        LocalDateTime now = LocalDateTime.now();
        CreateReviewRequest request = new CreateReviewRequest(memberId, postId, content, now);

        reviewService.createReview(request);

    }

}

