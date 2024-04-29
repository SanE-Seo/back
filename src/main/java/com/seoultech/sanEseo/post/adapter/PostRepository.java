package com.seoultech.sanEseo.post.adapter;

import com.seoultech.sanEseo.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    boolean existsByTitleAndDescription(String title, String description);
}
