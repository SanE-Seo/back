package com.seoultech.sanEseo.post.adapter;

import com.seoultech.sanEseo.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    boolean existsByTitleAndDescription(String title, String description);

    List<Post> findByMemberId(Long id);
}
