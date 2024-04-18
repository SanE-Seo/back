package com.seoultech.sanEseo.like;

import org.springframework.http.HttpStatus;
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
    public ResponseEntity<Void> addLike(@RequestBody AddLikeRequest request) {
        likeService.addLike(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/likes/{likeId}")
    public ResponseEntity<Void> deleteLike(@PathVariable Long likeId) {
        likeService.deleteLike(likeId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/posts/{postId}/likes")
    public ResponseEntity<GetLikeResponse> getLikeCount(@PathVariable Long postId) {
        int likeCount = likeService.getLikeCount(postId);
        return ResponseEntity.ok(new GetLikeResponse(postId, likeCount));
    }
}
