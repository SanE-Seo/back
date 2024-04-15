package com.seoultech.sanEseo.post.application.service;

import com.seoultech.sanEseo.post.application.port.PostPort;
import com.seoultech.sanEseo.post.domain.Post;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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


    public GetPostResponse getPost(Long postId) {
        final Post post = postPort.getPost(postId);

        return new GetPostResponse(post.getId(), post.getCategory(), post.getTitle(), post.getSubTitle(), post.getDescription(), post.getLevel(), post.getTime(), post.getCoordinate(), post.getImages());
    }
}
