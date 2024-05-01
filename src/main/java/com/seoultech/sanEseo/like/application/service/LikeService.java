package com.seoultech.sanEseo.like.application.service;


import com.seoultech.sanEseo.like.application.port.LikePort;
import com.seoultech.sanEseo.like.domain.Likes;
import com.seoultech.sanEseo.like.exception.DuplicateLikesException;
import com.seoultech.sanEseo.like.exception.MemberNotLikedException;
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

    public void addLike(Long memberId, Long postId) {

        Member member = memberPort.loadById(memberId);
        Post post = postPort.getPost(postId);

        boolean isLiked = likePort.existsByPostAndMember(post, member);
        if(isLiked) {
            throw new DuplicateLikesException("이미 좋아요 한 게시글입니다.");
        }

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

        boolean isLiked = likePort.existsByPostAndMember(post, member);
        if(!isLiked) {
            throw new MemberNotLikedException("좋아요 하지 않은 게시글입니다.");
        }

        likePort.deleteByPostAndMember(post, member);
    }

    public int getLikeCount(Long postId) {
        Post post = postPort.getPost(postId);
        return likePort.countByPost(post);
    }

    public boolean hasMemberLikedPost(Long memberId, Long postId) {
        Member member = memberPort.loadById(memberId);
        Post post = postPort.getPost(postId);
        return likePort.existsByPostAndMember(post, member);
    }

}
