package com.seoultech.sanEseo.district.domain;

import com.seoultech.sanEseo.weather.domain.PollutionAPI;
import com.seoultech.sanEseo.weather.domain.WeatherAPI;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class District {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @OneToOne(mappedBy = "district")
    private WeatherAPI weatherAPI;
    @OneToOne(mappedBy = "district")
    private PollutionAPI pollutionAPI;

    @Builder
    public District(String name) {
        this.name = name;
    }
}
