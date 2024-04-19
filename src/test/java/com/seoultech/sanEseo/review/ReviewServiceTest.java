package com.seoultech.sanEseo.review;

import com.seoultech.sanEseo.district.DistrictSteps;
import com.seoultech.sanEseo.district.application.service.DistrictService;
import com.seoultech.sanEseo.member.MemberSteps;
import com.seoultech.sanEseo.member.application.port.out.MemberPort;
import com.seoultech.sanEseo.member.application.service.MemberService;
import com.seoultech.sanEseo.member.domain.Member;
import com.seoultech.sanEseo.post.PostSteps;
import com.seoultech.sanEseo.post.application.port.PostPort;
import com.seoultech.sanEseo.post.application.service.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class ReviewServiceTest {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private PostService postService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private DistrictService districtService;

    @Test
    void 리뷰등록() {

        districtService.createDistrict(DistrictSteps.자치구등록요청_생성());
        districtService.createDistrict(DistrictSteps.자치구등록요청_생성());

        postService.addPost(PostSteps.게시글등록요청_생성());
        memberService.addMember(MemberSteps.사용자등록요청_생성());
        CreateReviewRequest request = 리뷰등록요청_생성();

        reviewService.createReview(request);

    }

    private static CreateReviewRequest 리뷰등록요청_생성() {
        Long memberId = 1L;
        Long postId = 1L;
        String content = "리뷰 내용";
        LocalDateTime now = LocalDateTime.now();
        CreateReviewRequest request = new CreateReviewRequest(memberId, postId, content, now);
        return request;
    }

}

