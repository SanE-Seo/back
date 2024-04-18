package com.seoultech.sanEseo.like;

import org.springframework.stereotype.Component;

@Component
public class LikeAdapter implements LikePort{

    private LikeRepository likeRepository;

    public LikeAdapter(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    @Override
    public void save(Likes likes) {
        likeRepository.save(likes);
    }

    @Override
    public void deleteById(Long likeId) {
        likeRepository.deleteById(likeId);
    }

}
