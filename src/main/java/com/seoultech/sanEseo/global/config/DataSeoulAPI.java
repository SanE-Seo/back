package com.seoultech.sanEseo.global.config;

import com.seoultech.sanEseo.weather.application.service.PollutionAPIResponse;
import com.seoultech.sanEseo.weather.application.service.WeatherAPIResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

public interface DataSeoulAPI {

    @GetExchange("/{serviceKey}/json/ListAirQualityByDistrictService/1/1/{code}/")
    PollutionAPIResponse getPollution(
            @PathVariable("serviceKey") String serviceKey,
            @PathVariable("code") int code
    );
}
