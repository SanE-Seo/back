package com.seoultech.sanEseo.weather.application.service;

import com.seoultech.sanEseo.district.application.service.DistrictService;
import com.seoultech.sanEseo.district.domain.District;
import com.seoultech.sanEseo.global.config.DataGoAPI;
import com.seoultech.sanEseo.global.config.DataSeoulAPI;
import com.seoultech.sanEseo.global.exception.BusinessException;
import com.seoultech.sanEseo.global.exception.ErrorType;
import com.seoultech.sanEseo.global.property.PublicDataProperty;
import com.seoultech.sanEseo.weather.adapter.in.web.WeatherResponse;
import com.seoultech.sanEseo.weather.domain.PollutionData;
import com.seoultech.sanEseo.weather.domain.WeatherAPIRequest;
import com.seoultech.sanEseo.weather.domain.WeatherData;
import com.seoultech.sanEseo.weather.exception.PublicAPIException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Slf4j
@RequiredArgsConstructor
@Service
public class WeatherService {

    private final PublicDataProperty publicDataProperty;
    private final DistrictService districtService;
    private final DataGoAPI dataGoAPI;
    private final DataSeoulAPI dataSeoulAPI;

    private ExecutorService executorService = Executors.newCachedThreadPool();

    public WeatherResponse getWeatherResponse(Long districtId) {
        District district = districtService.findById(districtId);

        Future<PollutionData> task1 = executorService.submit(() -> getPollution(district.getPollutionAPI().getCode()));
        Future<WeatherData> task2 = executorService.submit(() -> getWeatherData(district.getWeatherAPI().getX(), district.getWeatherAPI().getY()));

        PollutionData pollutionData = null;
        WeatherData weatherData = null;
        try {
            pollutionData = task1.get();
            weatherData = task2.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new BusinessException(ErrorType.INTERNAL_ERROR, "비동기 처리 오류");
        }

        return WeatherResponse.builder()
                .districtId(districtId)
                .districtName(district.getName())
                .temperature(weatherData.getTemperature())
                .temperatureMax(weatherData.getTemperatureMax())
                .temperatureMin(weatherData.getTemperatureMin())
                .precipitation(weatherData.getPrecipitation())
                .humidity(weatherData.getHumidity())
                .microDust(pollutionData.getMicroDust())
                .ultraMicroDust(pollutionData.getUltraMicroDust())
                .ozone(pollutionData.getOzone())
                .build();
    }

    public PollutionData getPollution(int code) {
        try {
            PollutionAPIResponse result = dataSeoulAPI.getPollution(
                    publicDataProperty.getSeoulAccessKey(),
                    code
            );
            log.info("result: {}", result);
            Float microDust = Float.parseFloat(result.getListAirQualityByDistrictService().getRow().get(0).getPm10());
            Float ultraMicroDust = Float.parseFloat(result.getListAirQualityByDistrictService().getRow().get(0).getPm25());
            Float ozone = Float.parseFloat(result.getListAirQualityByDistrictService().getRow().get(0).getOzone());
            log.info("PM10: {}, PM25: {}, O3: {}", microDust, ultraMicroDust, ozone);

            return new PollutionData(microDust, ultraMicroDust, ozone);
        } catch (Exception e) {
            e.printStackTrace();
            throw new PublicAPIException("대기 정보를 불러올 수 없습니다.");
        }
    }

    public WeatherData getWeatherData(int nx, int ny) {

        try {
            String baseDate = getBaseDate();
            String baseTime = getBaseTime();

            String fcstDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
            String fcstTime = LocalTime.now().plusHours(1).format(DateTimeFormatter.ofPattern("HH00"));

            WeatherAPIRequest request = WeatherAPIRequest.builder()
                    .serviceKey(publicDataProperty.getAccessKey())
                    .baseDate(baseDate)
                    .baseTime(baseTime)
                    .nx(nx)
                    .ny(ny)
                    .build();

            Future<WeatherAPIResponse> task1 = executorService.submit(() -> dataGoAPI.getWeather(
                    request.getServiceKey(),
                    request.getBaseDate(),
                    request.getBaseTime(),
                    request.getNx(),
                    request.getNy()
            ));

            String tmpMaxMinBaseDate = getTmpMaxMinBaseDate();
            String tmpMaxMinBaseTime = getTmpMaxMinBaseTime();

            WeatherAPIRequest newRequest = WeatherAPIRequest.builder()
                    .serviceKey(publicDataProperty.getAccessKey())
                    .baseDate(tmpMaxMinBaseDate)
                    .baseTime(tmpMaxMinBaseTime)
                    .nx(nx)
                    .ny(ny)
                    .build();

            Future<WeatherAPIResponse> task2 = executorService.submit(() -> dataGoAPI.getWeather(
                newRequest.getServiceKey(),
                newRequest.getBaseDate(),
                newRequest.getBaseTime(),
                newRequest.getNx(),
                newRequest.getNy()));


            WeatherAPIResponse result = task1.get();
            Float tmp = Float.parseFloat(result.getValueByCategoryAndDateTime("TMP", fcstDate, fcstTime));
            String pcp = result.getValueByCategoryAndDateTime("PCP", fcstDate, fcstTime);
            int reh = Integer.parseInt(result.getValueByCategoryAndDateTime("REH", fcstDate, fcstTime));


            WeatherAPIResponse newResult = task2.get();
            Float tmx = Float.parseFloat(newResult.getValueByCategoryAndDate("TMX", fcstDate));
            Float tmn = Float.parseFloat(newResult.getValueByCategoryAndDate("TMN", fcstDate));

            return new WeatherData(tmp, tmx, tmn, pcp, reh);

        } catch (Exception e) {
            e.printStackTrace();
            throw new PublicAPIException("기상 정보를 불러올 수 없습니다.");
        }

    }

    private String getToday(int minusDays) {
        LocalDate today = LocalDate.now().minusDays(minusDays);
        return today.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

    private List<Integer> baseHours = List.of(2, 5, 8, 11, 14, 17, 20, 23);

    private String getBaseDate() {
        LocalTime now = LocalTime.now();
        boolean isToday = now.isAfter(LocalTime.of(baseHours.get(0), 30));

        LocalDate today = LocalDate.now();
        if(!isToday) {
            today = today.minusDays(1);
        }
        return today.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

    private String getBaseTime() {
        LocalTime now = LocalTime.now();
        int result = baseHours.get(baseHours.size() - 1);
        for(int baseHour : baseHours) {
            if(now.isAfter(LocalTime.of(baseHour, 30))) {
                result = baseHour;
            }
        }
        return String.format("%02d00", result);
    }

    private String getTmpMaxMinBaseDate() {
        LocalTime now = LocalTime.now();
        boolean isToday = now.isAfter(LocalTime.of(2, 30));
        LocalDate today = LocalDate.now();
        if(!isToday) {
            today = today.minusDays(1);
        }
        return today.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

    private String getTmpMaxMinBaseTime() {
        LocalTime now = LocalTime.now();
        int result = 0;
        if(now.isAfter(LocalTime.of(2, 30))) {
            result = baseHours.get(0);
        } else {
            result = baseHours.get(baseHours.size() - 1);
        }
        return String.format("%02d00", result);
    }
}
