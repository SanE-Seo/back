package com.seoultech.sanEseo.like.application.port;

import com.seoultech.sanEseo.like.domain.Likes;
import com.seoultech.sanEseo.member.domain.Member;
import com.seoultech.sanEseo.post.domain.Post;

public interface LikePort {

    void save(Likes likes);

    void deleteByPostAndMember(Post post, Member member);

    int countByPostId(Long postId);
}
