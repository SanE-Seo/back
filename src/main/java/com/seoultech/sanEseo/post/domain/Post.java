package com.seoultech.sanEseo.post.domain;

import jakarta.persistence.*;
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
    private float distance;
    private int level;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Coordinate coordinate;

    private String time;

    @OneToMany(cascade = CascadeType.ALL)
    private List<PostImage> images;

    public Post(Category category, String title, String subTitle, String description, int level, String time, float distance, Coordinate coordinate, List<PostImage> images) {
        this.category = category;
        this.title = title;
        this.subTitle = subTitle;
        this.description = description;
        this.level = level;
        this.time = time;
        this.distance = distance;
        this.coordinate = coordinate;
        this.images = images;
    }

    public void update(Category category, String title, String subTitle, String description, int level, String time, float distance, Coordinate coordinate, Iterable<PostImage> images) {
        this.category = category;
        this.title = title;
        this.subTitle = subTitle;
        this.description = description;
        this.level = level;
        this.time = time;
        this.distance = distance;
        this.coordinate = coordinate;
        this.images = (List<PostImage>) images;

    }

}
