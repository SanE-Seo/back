package com.seoultech.sanEseo.image;


import com.seoultech.sanEseo.post.domain.Post;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class PostImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageUrl;

    @ManyToOne(fetch = FetchType.EAGER)//오류 때문에 LAZY -> EAGER
    @JoinColumn(name = "post_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Post post;

    public PostImage(String imageUrl, Post post) {
        this.imageUrl = imageUrl;
        this.post = post;
    }
}
