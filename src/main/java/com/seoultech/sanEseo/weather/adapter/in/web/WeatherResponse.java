package com.seoultech.sanEseo.weather.adapter.in.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WeatherResponse {
    private Long districtId;
    private String districtName;
    private Float temperature;
    private Float temperatureMax;
    private Float temperatureMin;
    private int precipitation;
    private int humidity;
    private Float microDust;
    private Float ultraMicroDust;
    private Float ozone;
}
