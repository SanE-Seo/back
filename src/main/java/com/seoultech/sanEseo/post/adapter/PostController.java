package com.seoultech.sanEseo.post.adapter;

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
    public ResponseEntity<Void> addPost(@RequestBody AddPostRequest request) {
        postService.addPost(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{postId}")
    public ResponseEntity<GetPostResponse> getPost(@PathVariable Long postId) {
        GetPostResponse response = postService.getPost(postId);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{postId}")
    public ResponseEntity<Void> updatePost(@PathVariable Long postId, @RequestBody UpdatePostRequest request) {
        postService.updatePost(postId, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.noContent().build();
    }
}
