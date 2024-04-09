package com.seoultech.sanEseo.post.adapter;

import com.seoultech.sanEseo.post.application.port.PostPort;
import com.seoultech.sanEseo.post.domain.Post;
import org.springframework.stereotype.Component;

@Component
public class PostAdapter implements PostPort {

    private final PostRepository postRepository;

    public PostAdapter(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public void save(Post post) {
        postRepository.save(post);
    }
}
