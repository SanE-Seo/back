package com.seoultech.sanEseo.like.adapter;

import com.seoultech.sanEseo.like.domain.Likes;
import com.seoultech.sanEseo.like.application.port.LikePort;
import com.seoultech.sanEseo.member.domain.Member;
import com.seoultech.sanEseo.post.domain.Post;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LikeAdapter implements LikePort {

    private LikeRepository likeRepository;

    public LikeAdapter(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    @Override
    public void save(Likes likes) {
        likeRepository.save(likes);
    }

    @Override
    public void deleteByPostAndMember(Post post, Member member) {
        likeRepository.deleteByPostAndMember(post, member);
    }

    @Override
    public int countByPostId(Long postId) {
        return likeRepository.countByPostId(postId);
    }

    @Override
    public boolean existsByPostIdAndMemberId(Long postId, Long memberId) {
        return likeRepository.existsByPostIdAndMemberId(postId, memberId);
    }


}
