package com.seoultech.sanEseo.weather.adapter.in.web;

import com.seoultech.sanEseo.global.response.ApiResponse;
import com.seoultech.sanEseo.weather.application.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/weather")
public class WeatherController {
    private final WeatherService weatherService;
    @GetMapping
    public ResponseEntity<?> getWeather(@RequestParam Long districtId) {
        return ApiResponse.ok("기상 정보 조회 성공", weatherService.getWeatherResponse(districtId));
    }

}
