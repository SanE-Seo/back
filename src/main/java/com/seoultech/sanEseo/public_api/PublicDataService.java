package com.seoultech.sanEseo.public_api;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.geojson.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PublicDataService {
    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;

    public List<GetLinearResponse> getLinearResponses(int dataIndex) {

        String apiUrl = "";
        if (dataIndex == 1){
            apiUrl = DataConstant.API_URL_Linear_HanYang;
        } else if (dataIndex == 2){
            apiUrl = DataConstant.API_URL_Linear_JiCheon;
        } else if (dataIndex == 3){
            apiUrl = DataConstant.API_URL_Linear_MunHwa;
        }

        String url = UriComponentsBuilder.fromHttpUrl(apiUrl).buildAndExpand(DataConstant.apiKey).toUriString();

        // API 요청을 통해 JSON 문자열을 가져옵니다.
        String jsonResponse = restTemplate.getForObject(url, String.class);
        List<GetLinearResponse> responses = new ArrayList<>();

        // ObjectMapper를 사용하여 JSON 문자열을 Java 객체로 변환합니다.
        try {
            LinearResponseWrapper response = mapper.readValue(jsonResponse, LinearResponseWrapper.class);
            responses = response.getData().getRows().stream()
                    .map(row -> new GetLinearResponse(row.getObjectId(), row.getName(), row.getLat(), row.getLng()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responses;
    }

    public List<GetGeometryResponse> parsingCoordinate(int dataIndex) {

        String geoJsonPathString = "";
        if (dataIndex == 1){
            geoJsonPathString = DataConstant.PATH_GEO_JSON_DodreamHanYang;
        } else if (dataIndex == 2){
            geoJsonPathString = DataConstant.PATH_GEO_JSON_DodreamJiCheon;
        } else if (dataIndex == 3){
            geoJsonPathString = DataConstant.PATH_GEO_JSON_DodreamMunHwa;
        }

        ObjectMapper mapper = new ObjectMapper();
        List<GetGeometryResponse> responses = new ArrayList<>();
        try {
            Path geoJsonPath = new ClassPathResource(geoJsonPathString).getFile().toPath();
            String jsonContent = Files.readString(geoJsonPath);
            FeatureCollection featureCollection = mapper.readValue(jsonContent, FeatureCollection.class);

            for (Feature feature : featureCollection.getFeatures()) {
                MultiLineString multiLineString = (MultiLineString) feature.getGeometry();
                String name = feature.getProperty("NAME");
                List<List<Double>> coordinate_list = new ArrayList<>();
                for (List<LngLatAlt> coordinates : multiLineString.getCoordinates()) {
                    for (LngLatAlt lngLatAlt : coordinates) {

                        double latitude = lngLatAlt.getLatitude();
                        double longitude = lngLatAlt.getLongitude();
                        coordinate_list.add(List.of(latitude, longitude));

                    }
                    responses.add(new GetGeometryResponse("polyline", name, coordinate_list));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return responses;
    }
}
