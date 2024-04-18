package com.seoultech.sanEseo.like;

public interface LikePort {

    void save(Likes likes);

    void deleteById(Long likeId);

    int countByPostId(Long postId);
}
