package com.seoultech.sanEseo.district;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/districts")
public class DistrictService {
    private final DistrictPort districtPort = new DistrictAdapter(new DistrictRepository());

    @PostMapping
    public ResponseEntity<Void> createDistrict(@RequestBody CreateDistrictRequest request) {
        final District district = new District(request.districtId(), request.districtName());
        districtPort.save(district);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
