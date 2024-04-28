package com.seoultech.sanEseo.post.adapter;

import com.seoultech.sanEseo.global.response.ApiResponse;
import com.seoultech.sanEseo.post.application.service.AddPostRequest;

import com.seoultech.sanEseo.post.application.service.GetPostResponse;
import com.seoultech.sanEseo.post.application.service.PostService;
import com.seoultech.sanEseo.post.application.service.UpdatePostRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<?> addPost(@RequestBody AddPostRequest request) {
        postService.addPost(request);
        return ApiResponse.ok("게시글이 추가되었습니다.");
    }

    @GetMapping("/{postId}")
    public ResponseEntity<?> getPost(@PathVariable Long postId) {
        GetPostResponse response = postService.getPost(postId);
        return ApiResponse.ok("게시글 조회 성공", response);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<?> updatePost(@PathVariable Long postId, @RequestBody UpdatePostRequest request) {
        postService.updatePost(postId, request);
        return ApiResponse.ok("게시글이 수정되었습니다.");
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return ApiResponse.ok("게시글이 삭제되었습니다.");
    }
}
