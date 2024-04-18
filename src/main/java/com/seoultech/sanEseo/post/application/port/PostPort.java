package com.seoultech.sanEseo.post.application.port;

import com.seoultech.sanEseo.post.domain.Post;

public interface PostPort {
    void save(Post post);

    Post getPost(Long postId);

    void deletePost(Long postId);
}
