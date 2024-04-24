package com.seoultech.sanEseo.post.application.port;

import com.seoultech.sanEseo.post.domain.Post;

import java.util.Optional;

public interface PostPort {
    void save(Post post);

    Post getPost(Long postId);

    void deletePost(Long postId);
}
