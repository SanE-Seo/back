package com.seoultech.sanEseo.district;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/districts")
public class DistrictService {
    private final DistrictPort districtPort;

    public DistrictService(DistrictPort districtPort) {
        this.districtPort = districtPort;
    }

    @PostMapping
    public ResponseEntity<Void> createDistrict(@RequestBody CreateDistrictRequest request) {
        final District district = new District(request.districtName());
        districtPort.save(district);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    @Transactional
    public ResponseEntity<List<GetDistrictResponse>> findAllDistricts() {
        final List<District> districts = districtPort.findAll();
        return ResponseEntity.ok(GetDistrictResponse.from(districts));
    }

    @DeleteMapping("/{districtId}")
    public ResponseEntity<Void> deleteDistrict(@PathVariable Long districtId) {
        districtPort.delete(districtId);
        return ResponseEntity.noContent().build();
    }
}
