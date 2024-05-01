package com.seoultech.sanEseo.weather.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@Builder
public class WeatherAPIRequest {
    private String serviceKey;
    private String baseDate;
    private String baseTime;
    private int nx;
    private int ny;
}
