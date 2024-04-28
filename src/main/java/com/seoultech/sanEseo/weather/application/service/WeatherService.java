package com.seoultech.sanEseo.weather.application.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seoultech.sanEseo.district.application.service.DistrictService;
import com.seoultech.sanEseo.district.domain.District;
import com.seoultech.sanEseo.global.config.DataGoAPI;
import com.seoultech.sanEseo.global.property.PublicDataProperty;
import com.seoultech.sanEseo.weather.adapter.in.web.WeatherResponse;
import com.seoultech.sanEseo.weather.exceptin.PublicAPIException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class WeatherService {

    private final PublicDataProperty publicDataProperty;
    private final DistrictService districtService;
    private final DataGoAPI dataGoAPI;

    public WeatherResponse getWeatherData(Long districtId) {
        District district = districtService.findById(districtId);

        PollutionResponse pollutionResponse = getPollution(district);
        // WeatherResponse weatherResponse = getWeather(district);
        return WeatherResponse.builder()
                .districtId(districtId)
                .districtName(district.getName())
                .temperature(16.0f)
                .temperatureMax(20.0f)
                .temperatureMin(10.0f)
                .precipitation(0)
                .humidity(50)
                .microDust(30.0f)
                .ultraMicroDust(20.0f)
                .ozone(0.03f)
                .build();
    }

    public PollutionResponse getPollution(District district) {

        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "http://openapi.seoul.go.kr:8088/" + publicDataProperty.getSeoulAccessKey() + "/json/ListAirQualityByDistrictService/1/1/" + district.getPollutionAPI().getCode() + "/";
        ResponseEntity<Map> response = restTemplate.getForEntity(apiUrl, Map.class);
        Map<String, Object> responseBody = response.getBody();
        if (responseBody != null) {
            Map<String, Object> airQualityData = (Map<String, Object>) responseBody.get("ListAirQualityByDistrictService");
            if (airQualityData != null) {
                List<Map<String, Object>> row = (List<Map<String, Object>>) airQualityData.get("row");
                if (row != null) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    return objectMapper.convertValue(row.get(0), PollutionResponse.class);
                }
            }
        }
        return null;
    }

    public Map<String, Object> getWeather(District district) {
        try {
            String dateString = getToday();
            String timeString = getRecentTime();
            int x = district.getWeatherAPI().getX();
            int y = district.getWeatherAPI().getY();
            // dateString, timeString, x, y 출력
            System.out.println(dateString);
            System.out.println(timeString);
            System.out.println(x);
            System.out.println(y);

            String key = publicDataProperty.getAccessKey();

            String result = dataGoAPI.getWeather(key, dateString, timeString, x, y);
            System.out.println(result);

            return null;

        } catch (Exception e) {
            e.printStackTrace();
            throw new PublicAPIException("기상 정보를 불러올 수 없습니다.");
        }

    }

    private String getToday() {
        LocalDate today = LocalDate.now();
        return today.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

    private String getRecentTime() {
        LocalTime currentTime = LocalTime.now();
        LocalTime roundedTime = currentTime.minusHours(5).withMinute(0).withSecond(0).withNano(0);
        return roundedTime.format(DateTimeFormatter.ofPattern("HHmm"));
    }
}
