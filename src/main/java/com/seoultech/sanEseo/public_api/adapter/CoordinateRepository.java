package com.seoultech.sanEseo.public_api.adapter;

import com.seoultech.sanEseo.post.domain.Post;
import com.seoultech.sanEseo.public_api.domain.Coordinate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoordinateRepository extends JpaRepository<Coordinate, Long> {
    Coordinate findByPost(Post post);
}
