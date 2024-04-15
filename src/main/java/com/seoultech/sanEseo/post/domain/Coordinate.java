package com.seoultech.sanEseo.post.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Coordinate {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    Long id;

    String name;
    double lat;
    double lng;
    String shape;

    public Coordinate(String name, double lat, double lng, String shape) {
        this.name = name;
        this.lat = lat;
        this.lng = lng;
        this.shape = shape;
    }
}
