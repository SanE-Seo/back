package com.seoultech.sanEseo.public_api;

import com.seoultech.sanEseo.global.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DataController {
    private final PublicDataService publicDataService;

    public DataController(PublicDataService publicDataService) {
        this.publicDataService = publicDataService;
    }

    @GetMapping("/data/{dataIndex}")
    public ResponseEntity<?> getData(@PathVariable int dataIndex) {
        List<GetLinearResponse> linearResponses = publicDataService.getLinearResponses(dataIndex);

        return ApiResponse.ok("선형 API 정보 가져오기 성공", linearResponses);
    }


    @GetMapping("/geometry/{dataIndex}")
    public ResponseEntity<?> getGeometryData(@PathVariable int dataIndex) {
        List<GetGeometryResponse> getGeometryResponses = publicDataService.parsingCoordinate(dataIndex);

        return ApiResponse.ok("좌표 정보 가져오기 성공", getGeometryResponses);
    }

    @GetMapping("/course/{dataIndex}")
    public ResponseEntity<?> getCourseData(@PathVariable int dataIndex) {
        List<GetCourseResponse> getCourseResponses = publicDataService.getCourseResponses(dataIndex);

        return ApiResponse.ok("코스 정보 가져오기 성공", getCourseResponses);
    }

    @GetMapping("/public/data/{dataIndex}")
    public ResponseEntity<?> addPublicData(@PathVariable int dataIndex) {
        publicDataService.addPublicData(dataIndex);

        return ApiResponse.ok("공공 API 정보 가져오기 성공", "공공 API 정보 만들기 성공");
    }




}
