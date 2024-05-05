package com.seoultech.sanEseo.like.adapter;

import com.seoultech.sanEseo.global.config.web.AuthMember;
import com.seoultech.sanEseo.global.config.web.LoginMember;
import com.seoultech.sanEseo.global.response.ApiResponse;
import com.seoultech.sanEseo.like.adapter.dto.GetMemberLikedPostResponse;
import com.seoultech.sanEseo.like.application.service.AddLikeRequest;
import com.seoultech.sanEseo.like.application.service.GetLikeResponse;
import com.seoultech.sanEseo.like.application.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;


    @PostMapping("/posts/{postId}/likes")
    public ResponseEntity<?> addLike(@LoginMember AuthMember member, @PathVariable Long postId) {
        likeService.addLike(member.getId(), postId);
        return ApiResponse.ok("좋아요가 추가되었습니다.");
    }

    @DeleteMapping("/posts/{postId}/likes")
    public ResponseEntity<?> deleteLike(@LoginMember AuthMember member, @PathVariable Long postId) {
        likeService.deleteLike(postId, member.getId());
        return ApiResponse.ok("좋아요가 삭제되었습니다.");
    }

    @GetMapping("/posts/{postId}/likes")
    public ResponseEntity<?> getLikeCount(@PathVariable Long postId) {
        int likeCount = likeService.getLikeCount(postId);
        return ApiResponse.ok("좋아요 수 조회 성공", new GetLikeResponse(postId, likeCount));
    }

    @GetMapping("/posts/{postId}/likes/me")
    public ResponseEntity<?> getMemberLikedPost(@LoginMember AuthMember authMember, @PathVariable Long postId) {
        boolean isLiked = likeService.hasMemberLikedPost(authMember.getId(), postId);
        return ApiResponse.ok("좋아요 여부 조회 성공", new GetMemberLikedPostResponse(isLiked));
    }

}
