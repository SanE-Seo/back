package com.seoultech.sanEseo.post.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    private String image;

    public Post(Category category, String title, String subTitle, String description, int level, String time, String image) {
        this.category = category;
        this.title = title;
        this.subTitle = subTitle;
        this.description = description;
        this.level = level;
        this.time = time;
        this.image = image;
    }
}
