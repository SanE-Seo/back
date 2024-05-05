package com.seoultech.sanEseo.post.adapter;

import com.seoultech.sanEseo.global.config.web.AuthMember;
import com.seoultech.sanEseo.global.config.web.LoginMember;
import com.seoultech.sanEseo.global.response.ApiResponse;
import com.seoultech.sanEseo.post.application.service.AddPostRequest;

import com.seoultech.sanEseo.post.application.service.GetPostResponse;
import com.seoultech.sanEseo.post.application.service.PostService;
import com.seoultech.sanEseo.post.application.service.UpdatePostRequest;
import com.seoultech.sanEseo.post_district.application.service.GetPostDistrictResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<?> addPost(@LoginMember AuthMember authMember, @RequestBody AddPostRequest request) {
        Long postId = postService.addPost(authMember.getId(), request);
        return ApiResponse.ok("게시글이 추가되었습니다.", postId);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<?> getPost(@PathVariable Long postId) {
        GetPostResponse response = postService.getPost(postId);
        return ApiResponse.ok("게시글 조회 성공", response);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<?> updatePost(@LoginMember AuthMember authMember, @PathVariable Long postId, @RequestBody UpdatePostRequest request) {
        postService.updatePost(authMember.getId(), postId, request);
        return ApiResponse.ok("게시글이 수정되었습니다.");
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<?> deletePost(@LoginMember AuthMember authMember, @PathVariable Long postId) {
        postService.deletePost(authMember.getId(), postId);
        return ApiResponse.ok("게시글이 삭제되었습니다.");
    }

}
