package com.seoultech.sanEseo.public_api;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.geojson.Feature;
import org.geojson.LineString;
import org.geojson.LngLatAlt;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.geojson.FeatureCollection;

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
    private final String apiKey = "426d4e43796d656e39326664716a53";
    private final String apiUrl = "http://openapi.seoul.go.kr:8088/{apiKey}/json/SdeDoDreamWay05LW/1/5/";

    public List<GetLinearResponse> fetchData() {
        String url = UriComponentsBuilder.fromHttpUrl(apiUrl).buildAndExpand(apiKey).toUriString();

        // API 요청을 통해 JSON 문자열을 가져옵니다.
        String jsonResponse = restTemplate.getForObject(url, String.class);
        List<GetLinearResponse> responses = new ArrayList<>();


        // ObjectMapper를 사용하여 JSON 문자열을 Java 객체로 변환합니다.
        try {
            LinearResponseWrapper response = mapper.readValue(jsonResponse, LinearResponseWrapper.class);
            responses = response.getData().getRows().stream()
                    .map(row -> new GetLinearResponse(row.getObjectId(), row.getName(), convertToGeoJson(row.getShape()), row.getLat(), row.getLng()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responses;
    }

    public FeatureCollection convertToGeoJson(String shape) {
        FeatureCollection featureCollection = new FeatureCollection();
        LineString lineString = new LineString();

        // 좌표 파싱
        String[] parts = shape.split(",");
        LngLatAlt start = new LngLatAlt(Double.parseDouble(parts[2]), Double.parseDouble(parts[3]));
        LngLatAlt end = new LngLatAlt(Double.parseDouble(parts[4]), Double.parseDouble(parts[5]));
        lineString.add(start);
        lineString.add(end);

//        Connection conn = DriverManager.getConnection(url, username, password);
//        PreparedStatement pstmt = conn.prepareStatement("SELECT blob_column FROM table_name WHERE id = ?");
//        pstmt.setInt(1, yourId);
//        ResultSet rs = pstmt.executeQuery();
//        if (rs.next()) {
//            Blob blob = rs.getBlob("blob_column");
//            InputStream inputStream = blob.getBinaryStream();
//            // 이제 inputStream을 통해 데이터를 읽고 처리할 수 있습니다.
//        }
//        rs.close();
//        pstmt.close();
//        conn.close();

        Feature feature = new Feature();
        feature.setGeometry(lineString);
        featureCollection.add(feature);

        return featureCollection;
    }
}
