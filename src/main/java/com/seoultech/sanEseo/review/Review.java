package com.seoultech.sanEseo.review;

import com.seoultech.sanEseo.member.domain.Member;
import com.seoultech.sanEseo.post.domain.Post;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    private String content;

    private LocalDateTime createDate;

    @Builder
    public Review(Member member, Post post, String content, LocalDateTime createDate) {
        this.member = member;
        this.post = post;
        this.content = content;
        this.createDate = createDate;
    }
}
