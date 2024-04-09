package com.seoultech.sanEseo.post.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Category category;
    private String title;
    private String subTitle;
    private String description;
    private int level;
    private String time;

    @OneToMany(cascade = CascadeType.ALL)
    private List<PostImage> images;

    public Post(Category category, String title, String subTitle, String description, int level, String time, List<PostImage> images) {
        this.category = category;
        this.title = title;
        this.subTitle = subTitle;
        this.description = description;
        this.level = level;
        this.time = time;
        this.images = images;
    }
}
