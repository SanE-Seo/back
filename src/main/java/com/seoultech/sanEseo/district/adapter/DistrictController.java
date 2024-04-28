package com.seoultech.sanEseo.district.adapter;

import com.seoultech.sanEseo.district.application.port.CreateDistrictRequest;
import com.seoultech.sanEseo.district.domain.District;
import com.seoultech.sanEseo.district.application.port.GetDistrictResponse;
import com.seoultech.sanEseo.district.application.port.DistrictPort;
import com.seoultech.sanEseo.global.response.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/districts")
public class DistrictController {
    private final DistrictPort districtPort;

    public DistrictController(DistrictPort districtPort) {
        this.districtPort = districtPort;
    }

    @PostMapping
    public ResponseEntity<?> createDistrict(@RequestBody @Valid CreateDistrictRequest request) {
        District district = new District(request.getDistrictName());
        districtPort.save(district);
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
