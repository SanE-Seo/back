package com.seoultech.sanEseo.public_api;


import com.seoultech.sanEseo.post.domain.Post;
import com.seoultech.sanEseo.public_api.CoordinatesConverter;
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
    private String lat;
    private String lng;
    private String type;

    @Convert(converter = CoordinatesConverter.class)
    @Column(columnDefinition = "TEXT")
    private List<List<Double>> coordinates;

    @OneToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public Coordinate(String name, String lat, String lng, String type, List<List<Double>> coordinates, Post post) {
        this.name = name;
        this.lat = lat;
        this.lng = lng;
        this.type = type;
        this.coordinates = coordinates;
        this.post = post;
    }
}