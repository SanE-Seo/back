package com.seoultech.sanEseo.public_api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seoultech.sanEseo.district.application.port.DistrictPort;
import com.seoultech.sanEseo.district.domain.District;
import com.seoultech.sanEseo.post.application.service.AddPostRequest;
import com.seoultech.sanEseo.post.application.service.PostService;
import com.seoultech.sanEseo.post.domain.Category;
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
    private final PostService postService;
    private final DistrictPort districtPort;

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
    public List<GetCourseResponse> getCourseResponses(int dataIndex) {

        String apiUrl = "";
        if (dataIndex == 1){
            apiUrl = DataConstant.API_URL_COURSE_HanYang;
        } else if (dataIndex == 2){
            apiUrl = DataConstant.API_URL_COURSE_JiCheon;
        } else if (dataIndex == 3){
            apiUrl = DataConstant.API_URL_COURSE_MunHwa;
        }

        String url = UriComponentsBuilder.fromHttpUrl(apiUrl).buildAndExpand(DataConstant.apiKey).toUriString();

        // API 요청을 통해 JSON 문자열을 가져옵니다.
        String jsonResponse = restTemplate.getForObject(url, String.class);
        List<GetCourseResponse> responses = new ArrayList<>();

        // ObjectMapper를 사용하여 JSON 문자열을 Java 객체로 변환합니다.
        try {
            CourseResponseWrapper response = mapper.readValue(jsonResponse, CourseResponseWrapper.class);
            responses = response.getData().getRows().stream()
                    .map(row -> new GetCourseResponse(row.getTitle(), row.getSubTitle(), row.getDistance(), row.getTime(), row.getLevel(), row.getDistrict(), row.getTransportation(), row.getCourseDetail(), row.getDescription()))
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

    public void addPublicData(int dataIndex){

        List<GetGeometryResponse> getGeometryResponses = parsingCoordinate(dataIndex);
        List<GetLinearResponse> linearResponses = getLinearResponses(dataIndex);
        List<GetCourseResponse> courseResponses = getCourseResponses(dataIndex);

        // GetCourseResponse를 가장 바깥쪽 루프로 이동
        for (GetCourseResponse getCourseResponse : courseResponses) {
            String courseName = normalizeName(getCourseResponse.getName()); // 이름 정규화 함수 사용
            System.out.println("courseName = " + courseName);
            // GetLinearResponse 리스트 처리
            for (GetLinearResponse getLinearResponse : linearResponses) {
                if (courseName.equals(normalizeName(getLinearResponse.getName()))) {

                    // GetGeometryResponse 리스트 처리
                    for (GetGeometryResponse getGeometryResponse : getGeometryResponses) {
                        System.out.println("getGeometryResponse.getName() = " + getGeometryResponse.getName());
                        if (courseName.equals(normalizeName(getGeometryResponse.getName()))) {

                            System.out.println("getCourseResponse.getDistrict() = " + getCourseResponse.getDistrict());
                            String district = getCourseResponse.getDistrict();
                            int commaIndex = district.indexOf(','); // 쉼표 위치 찾기

                            if (commaIndex != -1) {
                                district = district.substring(0, commaIndex); // 쉼표 이전까지 문자열 자르기
                            }
                            if (!district.isEmpty()) {
                                District byName = districtPort.findByName(district); // DB에서 District 조회
                                Long id = byName.getId(); // District ID 가져오기
                                // 데이터베이스에 Post 추가

                                postService.addPost(new AddPostRequest(
                                        Category.DODREAM, getGeometryResponse.getName(), getCourseResponse.getSubTitle(),
                                        safeSubstring(getCourseResponse.getDescription(), 0, 255),
                                        getCourseResponse.getLevel(), getCourseResponse.getTime(),
                                        getCourseResponse.getDistance(), safeSubstring(getCourseResponse.getCourseDetail(), 0 ,255),
                                        getCourseResponse.getTransportation(), id));
                            }
                        }
                    }
                }
            }
        }
    }

    private String normalizeName(String name) {
        return name.replace(" ", "").toLowerCase(); // 공백 제거 및 소문자로 변환
    }

    public String safeSubstring(String str, int start, int end) {
        if (str == null) return null;  // 널 문자열을 처리

        // 실제 문자열 길이를 벗어나지 않도록 범위를 조정
        if (start < 0) start = 0;
        if (end > str.length()) end = str.length();
        if (start > end) start = end;  // 시작 인덱스가 끝 인덱스보다 큰 경우, 둘을 교환

        return str.substring(start, end);
    }



}
