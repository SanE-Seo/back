package com.seoultech.sanEseo.review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByPostId(Long postId);
}
