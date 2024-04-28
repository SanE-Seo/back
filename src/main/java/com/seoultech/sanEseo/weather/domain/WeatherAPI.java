package com.seoultech.sanEseo.weather.domain;

import com.seoultech.sanEseo.district.domain.District;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class WeatherAPI {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int x;
    private int y;
    @OneToOne
    private District district;
}
