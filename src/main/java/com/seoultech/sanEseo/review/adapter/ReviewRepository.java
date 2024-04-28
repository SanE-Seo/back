package com.seoultech.sanEseo.review.adapter;

import com.seoultech.sanEseo.member.domain.Member;
import com.seoultech.sanEseo.post.domain.Post;
import com.seoultech.sanEseo.review.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByPostId(Long postId);

    void deleteByPostAndMember(Post post, Member member);
}
