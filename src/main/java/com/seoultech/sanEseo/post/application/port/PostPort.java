package com.seoultech.sanEseo.post.application.port;

import com.seoultech.sanEseo.post.domain.Post;

public interface PostPort {
    default void save(Post post) {

    }
}
