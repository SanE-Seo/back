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
    private String type;

    @ElementCollection
    @CollectionTable(name = "coordinate_points", joinColumns = @JoinColumn(name = "coordinate_id"))
    private List<LatLng> coordinates;

    @OneToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public Coordinate(String name, String type, List<LatLng> coordinates, Post post) {
        this.name = name;
        this.type = type;
        this.coordinates = coordinates;
        this.post = post;
    }
}