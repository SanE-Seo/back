package com.seoultech.sanEseo.review.adapter;


import com.seoultech.sanEseo.global.config.web.AuthMember;
import com.seoultech.sanEseo.global.config.web.LoginMember;
import com.seoultech.sanEseo.global.response.ApiResponse;
import com.seoultech.sanEseo.review.application.service.CreateReviewRequest;
import com.seoultech.sanEseo.review.application.service.GetReviewResponse;
import com.seoultech.sanEseo.review.application.service.ReviewService;
import com.seoultech.sanEseo.review.application.service.UpdateReviewRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/reviews")
    public ResponseEntity<?> createReview(@LoginMember AuthMember authMember, @RequestBody @Valid CreateReviewRequest request) {
        reviewService.createReview(authMember.getId(), request);
        return ApiResponse.ok("리뷰가 성공적으로 생성되었습니다.");
    }

    @DeleteMapping("/posts/{postId}/members/{memberId}/reviews")
    public ResponseEntity<?> deleteReview(@LoginMember AuthMember authMember, @PathVariable Long postId) {
        reviewService.deleteReview(postId, authMember.getId());
        return ApiResponse.ok("리뷰가 성공적으로 삭제되었습니다.");
    }

    @PutMapping("/posts/{postId}/members/{memberId}/reviews")
    public ResponseEntity<?> updateReview(@LoginMember AuthMember authMember, @PathVariable Long postId, @RequestBody UpdateReviewRequest request) {
        reviewService.updateReview(postId, authMember.getId(), request);
        return ApiResponse.ok("리뷰가 성공적으로 업데이트되었습니다.");
    }

    @GetMapping("/posts/{postId}/reviews")
    public ResponseEntity<?> getReviews(@PathVariable Long postId) {
        List<GetReviewResponse> responses = reviewService.getReviewList(postId);
        return ApiResponse.ok("리뷰 목록 조회 성공", responses);
    }
}
