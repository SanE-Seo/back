package com.seoultech.sanEseo.review.adapter;


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
    public ResponseEntity<?> createReview(@RequestBody @Valid CreateReviewRequest request) {
        reviewService.createReview(request);
        return ApiResponse.ok("리뷰가 성공적으로 생성되었습니다.");
    }

    @DeleteMapping("/posts/{postId}/members/{memberId}/reviews")
    public ResponseEntity<?> deleteReview(@PathVariable Long postId, @PathVariable Long memberId) {
        reviewService.deleteReview(postId, memberId);
        return ApiResponse.ok("리뷰가 성공적으로 삭제되었습니다.");
    }

    @PutMapping("/posts/{postId}/members/{memberId}/reviews")
    public ResponseEntity<?> updateReview(@PathVariable Long postId, @PathVariable Long memberId, @RequestBody UpdateReviewRequest request) {
        reviewService.updateReview(postId, memberId, request);
        return ApiResponse.ok("리뷰가 성공적으로 업데이트되었습니다.");
    }

    @GetMapping("/posts/{postId}/reviews")
    public ResponseEntity<?> getReviews(@PathVariable Long postId) {
        List<GetReviewResponse> responses = reviewService.getReviewList(postId);
        return ApiResponse.ok("리뷰 목록 조회 성공", responses);
    }
}
