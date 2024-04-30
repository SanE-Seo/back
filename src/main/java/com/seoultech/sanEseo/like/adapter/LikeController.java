package com.seoultech.sanEseo.like.adapter;

import com.seoultech.sanEseo.global.response.ApiResponse;
import com.seoultech.sanEseo.like.application.service.AddLikeRequest;
import com.seoultech.sanEseo.like.application.service.GetLikeResponse;
import com.seoultech.sanEseo.like.application.service.LikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LikeController {

    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping("/likes")
    public ResponseEntity<?> addLike(@RequestBody AddLikeRequest request) {
        likeService.addLike(request);
        return ApiResponse.ok("좋아요가 추가되었습니다.");
    }

    @DeleteMapping("/posts/{postId}/members/{memberId}/likes")
    public ResponseEntity<?> deleteLike(@PathVariable Long postId, @PathVariable Long memberId) {
        likeService.deleteLike(postId, memberId);
        return ApiResponse.ok("좋아요가 삭제되었습니다.");
    }

    @GetMapping("/posts/{postId}/likes")
    public ResponseEntity<?> getLikeCount(@PathVariable Long postId) {
        int likeCount = likeService.getLikeCount(postId);
        return ApiResponse.ok("좋아요 수 조회 성공", new GetLikeResponse(postId, likeCount));
    }

    @GetMapping("/posts/{postId}/members/{memberId}/likes")
    public ResponseEntity<?> hasMemberLikedPost(@PathVariable Long postId, @PathVariable Long memberId) {
        boolean hasLiked = likeService.hasMemberLikedPost(memberId, postId);
        return ApiResponse.ok("좋아요 여부 조회 성공", hasLiked);
    }

}
