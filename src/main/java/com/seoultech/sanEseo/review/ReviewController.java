package com.seoultech.sanEseo.review;


import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<Void> createReview(@RequestBody CreateReviewRequest request) {
        reviewService.createReview(request);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/posts/{postId}/members/{memberId}/reviews")
    public ResponseEntity<Void> deleteReview(@PathVariable Long postId, @PathVariable Long memberId) {
        reviewService.deleteReview(postId, memberId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/posts/{postId}/members/{memberId}/reviews")
    public ResponseEntity<Void> updateReview(@PathVariable Long postId, @PathVariable Long memberId, @RequestBody UpdateReviewRequest request) {
        reviewService.updateReview(postId, memberId, request);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/posts/{postId}/reviews")
    public ResponseEntity<List<GetReviewResponse>> getReview(@PathVariable Long postId) {
        List<GetReviewResponse> response = reviewService.getReviewList(postId);
        return ResponseEntity.ok(response);
    }

}
