package com.seoultech.sanEseo.post.application.service;

import com.seoultech.sanEseo.post.application.port.PostPort;
import com.seoultech.sanEseo.post.domain.Post;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/posts")
public class PostService {
    private final PostPort postPort;

    public PostService(PostPort postPort) {
        this.postPort = postPort;
    }

    @PostMapping
    public ResponseEntity<Void> addPost(@RequestBody AddPostRequest request) {
        final Post post = new Post(request.category(), request.title(), request.subTitle(), request.description(), request.level(), request.time(), request.coordinate(), request.images());

        postPort.save(post);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @GetMapping("/{postId}")
    public ResponseEntity<GetPostResponse> getPost(@PathVariable Long postId) {
        final Post post = postPort.getPost(postId);

        final GetPostResponse response = new GetPostResponse(post.getId(), post.getCategory(), post.getTitle(), post.getSubTitle(), post.getDescription(), post.getLevel(), post.getTime(), post.getCoordinate(), post.getImages());

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{postId}")
    @Transactional
    public ResponseEntity<Void> updatePost(@PathVariable Long postId, @RequestBody UpdatePostRequest request) {
        final Post post = postPort.getPost(postId);
        post.update(request.category(), request.title(), request.subTitle(), request.description(), request.coordinate(), request.images());

        postPort.save(post);
        return ResponseEntity.ok().build();
    }


}
