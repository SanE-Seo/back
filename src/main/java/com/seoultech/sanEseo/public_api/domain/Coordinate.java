package com.seoultech.sanEseo.public_api.domain;


import com.seoultech.sanEseo.post.domain.Post;
import com.seoultech.sanEseo.public_api.application.service.dto.CoordinatesConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Coordinate {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;


    @Convert(converter = CoordinatesConverter.class)
    @Column(columnDefinition = "TEXT")
    private List<List<Double>> coordinates;

    @OneToOne(orphanRemoval = true)
    private Post post;

    public Coordinate(String name, String type, List<List<Double>> coordinates, Post post) {
        this.name = name;
        this.type = type;
        this.coordinates = coordinates;
        this.post = post;
    }
    public void update(String name, String type, List<List<Double>> coordinates) {
        this.name = name;
        this.type = type;
        this.coordinates = coordinates;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}