package com.seoultech.sanEseo.public_api;

import com.seoultech.sanEseo.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoordinateRepository extends JpaRepository<Coordinate, Long> {
    Coordinate findByPost(Post post);
}
