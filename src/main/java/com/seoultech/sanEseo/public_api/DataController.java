package com.seoultech.sanEseo.public_api;

import com.seoultech.sanEseo.global.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DataController {
    private final PublicDataService publicDataService;

    public DataController(PublicDataService publicDataService) {
        this.publicDataService = publicDataService;
    }

    @GetMapping("/data")
    public ResponseEntity<?> getData() {
        List<GetLinearResponse> getLinearResponses = publicDataService.fetchData();

        return ApiResponse.ok("선형 API 정보 가져오기 성공", getLinearResponses);
    }
}
