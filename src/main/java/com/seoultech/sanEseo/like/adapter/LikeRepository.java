package com.seoultech.sanEseo.like.adapter;

import com.seoultech.sanEseo.like.domain.Likes;
import com.seoultech.sanEseo.member.domain.Member;
import com.seoultech.sanEseo.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Likes, Long> {
    int countByPost(Post post);
    void deleteByPostAndMember(Post post, Member member);

    boolean existsByPostAndMember(Post post, Member member);

    List<Likes> findByMemberId(Long memberId);
}
