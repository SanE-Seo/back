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
    private String level;
    private String time;
    private String distance;
    private String courseDetail;
    private String transportation;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Coordinate coordinate;


    @OneToMany(cascade = CascadeType.ALL)
    private List<PostImage> images;

    public Post(Category category, String title, String subTitle, String description, String level, String time, String distance,String courseDetail, String transportation, Coordinate coordinate, List<PostImage> images) {
        this.category = category;
        this.title = title;
        this.subTitle = subTitle;
        this.description = description;
        this.level = level;
        this.time = time;
        this.distance = distance;
        this.courseDetail = courseDetail;
        this.transportation = transportation;
        this.coordinate = coordinate;
        this.images = images;
    }

    public void update(Category category, String title, String subTitle, String description, String level, String time, String distance, String courseDetail, String transportation,Coordinate coordinate, Iterable<PostImage> images) {
        this.category = category;
        this.title = title;
        this.subTitle = subTitle;
        this.description = description;
        this.level = level;
        this.time = time;
        this.distance = distance;
        this.courseDetail = courseDetail;
        this.transportation = transportation;
        this.coordinate = coordinate;
        this.images = (List<PostImage>) images;

    }

}
