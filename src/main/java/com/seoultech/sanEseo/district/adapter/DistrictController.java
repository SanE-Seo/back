package com.seoultech.sanEseo.district.adapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seoultech.sanEseo.district.domain.District;
import com.seoultech.sanEseo.district.application.port.GetDistrictResponse;
import com.seoultech.sanEseo.district.application.port.DistrictPort;
import com.seoultech.sanEseo.global.response.ApiResponse;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/districts")
public class DistrictController {
    private final DistrictPort districtPort;
    private final ResourceLoader resourceLoader;


    public DistrictController(DistrictPort districtPort, ResourceLoader resourceLoader) {
        this.districtPort = districtPort;
        this.resourceLoader = resourceLoader;
    }

    @PostMapping
    public ResponseEntity<?> createDistrict() {
        List<String> districts = Arrays.asList("강남구", "강동구", "강북구", "강서구", "관악구", "광진구", "구로구", "금천구", "노원구",
                "도봉구", "동대문구", "동작구", "마포구", "서대문구", "서초구", "성동구", "성북구",
                "송파구", "양천구", "영등포구", "용산구", "은평구", "종로구", "중구", "중랑구");
        districts.forEach(name -> districtPort.save(new District(name)));

        return ApiResponse.ok("자치구가 생성되었습니다.");
    }

    @PostMapping("/custom")
    public ResponseEntity<?> createCustomDistrict() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:districts.json");
        ObjectMapper mapper = new ObjectMapper();
        List<String> districts = mapper.readValue(resource.getInputStream(), List.class);

        districts.forEach(name -> districtPort.save(new District(name)));

        return ApiResponse.ok("자치구가 생성되었습니다.");
    }


    @GetMapping
    public ResponseEntity<?> findAllDistricts() {
        List<District> districts = districtPort.findAll();
        List<GetDistrictResponse> responses = GetDistrictResponse.from(districts);
        return ApiResponse.ok("모든 자치구 조회 성공", responses);
    }

    @DeleteMapping("/{districtId}")
    public ResponseEntity<?> deleteDistrict(@PathVariable Long districtId) {
        districtPort.delete(districtId);
        return ApiResponse.ok("자치구가 삭제되었습니다.");
    }
}
