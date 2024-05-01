package com.seoultech.sanEseo.weather.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class WeatherData {
    private Float temperature;
    private Float temperatureMax;
    private Float temperatureMin;
    private String precipitation;
    private int humidity;
}
