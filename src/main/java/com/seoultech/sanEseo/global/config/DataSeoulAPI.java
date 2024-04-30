package com.seoultech.sanEseo.global.config;

import com.seoultech.sanEseo.weather.application.service.PollutionAPIResponse;
import com.seoultech.sanEseo.weather.application.service.WeatherAPIResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

public interface DataSeoulAPI {

    @GetExchange("/1360000/VilageFcstInfoService_2.0/getVilageFcst?serviceKey={serviceKey}&pageNo=1&numOfRows=1000&dataType=json&base_date={base_date}&base_time={base_time}&nx={nx}&ny={ny}")
    WeatherAPIResponse getWeather(
            @PathVariable("serviceKey") String serviceKey,
            @PathVariable("base_date") String baseDate,
            @PathVariable("base_time") String baseTime,
            @PathVariable("nx") int nx,
            @PathVariable("ny") int ny
    );

    @GetExchange("/{serviceKey}/json/ListAirQualityByDistrictService/1/1/{code}/")
    PollutionAPIResponse getPollution(
            @PathVariable("serviceKey") String serviceKey,
            @PathVariable("code") int code
    );
}
