package com.seoultech.sanEseo.review;

import com.seoultech.sanEseo.member.domain.Member;
import com.seoultech.sanEseo.post.domain.Post;

import java.time.LocalDateTime;

class Review {
    private Long id;
    private Member member;
    private Post post;
    private String content;
    private LocalDateTime createDate;

    public Review(Member member, Post post, String content, LocalDateTime createDate) {
        this.member = member;
        this.post = post;
        this.content = content;
        this.createDate = createDate;
    }

    public Long getId() {
        return id;
    }

    public void assignId(Long id) {
        this.id = id;
    }
}
