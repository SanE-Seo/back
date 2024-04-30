package com.seoultech.sanEseo.weather.application.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class PollutionAPIResponse {

    @JsonProperty("ListAirQualityByDistrictService")
    private ListAirQualityByDistrictService listAirQualityByDistrictService;

    @Data
    public static class ListAirQualityByDistrictService {
        @JsonProperty("list_total_count")
        private int count;

        private List<AirQualityData> row;
    }

    @Data
    public static class AirQualityData {
        @JsonProperty("MSRDATE")
        private String date;
        @JsonProperty("MSRADMCODE")
        private String districtCode;
        @JsonProperty("MSRSTENAME")
        private String districtName;
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
    }
}
