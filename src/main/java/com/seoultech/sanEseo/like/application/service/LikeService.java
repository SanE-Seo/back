package com.seoultech.sanEseo.like.application.service;


import com.seoultech.sanEseo.like.application.port.LikePort;
import com.seoultech.sanEseo.like.domain.Likes;
import com.seoultech.sanEseo.member.application.port.out.MemberPort;
import com.seoultech.sanEseo.member.domain.Member;
import com.seoultech.sanEseo.post.application.port.PostPort;
import com.seoultech.sanEseo.post.domain.Post;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor

public class LikeService {

    private final LikePort likePort;
    private final MemberPort memberPort;
    private final PostPort postPort;



    public void addLike(AddLikeRequest request) {

        // request를 Like 객체로 변환
        Long postId = request.getPostId();
        Long memberId = request.getMemberId();

        Member member = memberPort.loadById(memberId);
        Post post = postPort.getPost(postId);

        Likes likes = Likes.builder()
                .member(member)
                .post(post)
                .build();

        likePort.save(likes);
    }

    @Transactional
    public void deleteLike(Long postId, Long memberId) {

        Member member = memberPort.loadById(memberId);
        Post post = postPort.getPost(postId);

        likePort.deleteByPostAndMember(post, member);
    }

    public int getLikeCount(Long postId) {
        return likePort.countByPostId(postId);
    }

}
