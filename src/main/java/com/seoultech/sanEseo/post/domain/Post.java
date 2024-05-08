package com.seoultech.sanEseo.post.domain;

import com.seoultech.sanEseo.like.domain.Likes;
import com.seoultech.sanEseo.member.domain.Member;
import com.seoultech.sanEseo.post_district.domain.PostDistrict;
import com.seoultech.sanEseo.public_api.domain.Coordinate;
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

    @ManyToOne
    private Member member;
    private Category category;
    private String title;
    private String subTitle;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String level;
    private String time;
    private String distance;
    private String courseDetail;
    private String transportation;

    @OneToOne
    @JoinColumn(name = "coordinate_id")
    private Coordinate coordinate;

    @OneToMany(mappedBy = "post",orphanRemoval = true)
    private List<PostDistrict> postDistricts;

    public Post(Member member, Category category, String title, String subTitle, String description, String level, String time, String distance, String courseDetail, String transportation) {
        this.member = member;
        this.category = category;
        this.title = title;
        this.subTitle = subTitle;
        this.description = description;
        this.level = level;
        this.time = time;
        this.distance = distance;
        this.courseDetail = courseDetail;
        this.transportation = transportation;
    }

    public void update(Category category, String title, String subTitle, String description, String level, String time, String distance, String courseDetail, String transportation) {
        this.category = category;
        this.title = title;
        this.subTitle = subTitle;
        this.description = description;
        this.level = level;
        this.time = time;
        this.distance = distance;
        this.courseDetail = courseDetail;
        this.transportation = transportation;

    }

    public void setCoordinate(Coordinate coordinate) {
        if (coordinate != null) {
            coordinate.setPost(this);
        }
        this.coordinate = coordinate;
    }

}
