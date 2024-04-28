package com.seoultech.sanEseo.like.adapter;

import com.seoultech.sanEseo.like.domain.Likes;
import com.seoultech.sanEseo.member.domain.Member;
import com.seoultech.sanEseo.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Likes, Long> {
    int countByPostId(Long postId);
    void deleteByPostAndMember(Post post, Member member);
}
