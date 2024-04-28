package com.seoultech.sanEseo.weather.application.service;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PollutionResponse {
    @JsonProperty("MSRDATE")
    private String date;

    @JsonProperty("MSRADMCODE")
    private String code;

    @JsonProperty("MSRSTENAME")
    private String district;

    @JsonProperty("MAXINDEX")
    private String maxIndex;

    @JsonProperty("GRADE")
    private String grade;

    @JsonProperty("POLLUTANT")
    private String pollutant;

    @JsonProperty("NITROGEN")
    private String nitrogen;

    @JsonProperty("OZONE")
    private String ozone;

    @JsonProperty("CARBON")
    private String carbon;

    @JsonProperty("SULFUROUS")
    private String sulfurous;

    @JsonProperty("PM10")
    private String pm10;

    @JsonProperty("PM25")
    private String pm25;

    // Getters and setters
}
