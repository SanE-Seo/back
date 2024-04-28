package com.seoultech.sanEseo.weather.domain;

import com.seoultech.sanEseo.district.domain.District;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class PollutionAPI {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int code;
    @OneToOne
    private District district;
}
