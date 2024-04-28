package com.seoultech.sanEseo.district.application.service;

import com.seoultech.sanEseo.district.application.port.DistrictPort;
import com.seoultech.sanEseo.district.domain.District;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DistrictService {

    private final DistrictPort districtPort;

    public void save(District district) {
        districtPort.save(district);
    }

    public District findById(Long districtId) {
        return districtPort.findById(districtId);
    }

    public List<District> findAll() {
        return districtPort.findAll();
    }

    public void delete(Long districtId) {
        districtPort.delete(districtId);
    }
}