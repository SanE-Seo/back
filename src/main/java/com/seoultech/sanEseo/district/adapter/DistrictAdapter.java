package com.seoultech.sanEseo.district.adapter;


import com.seoultech.sanEseo.district.domain.District;
import com.seoultech.sanEseo.district.application.port.DistrictPort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class DistrictAdapter implements DistrictPort {
    private final DistrictRepository districtRepository;

    public DistrictAdapter(DistrictRepository districtRepository) {
        this.districtRepository = districtRepository;
    }

    @Override
    public void save(District district) {
        districtRepository.save(district);
    }

    @Override
    public List<District> findAll() {
        return districtRepository.findAll();
    }

    @Override
    public void delete(Long districtId) {
        districtRepository.deleteById(districtId);
    }

    @Override
    public District findById(Long districtId) {
        return districtRepository.findById(districtId).orElseThrow(() -> new IllegalArgumentException("해당 자치구가 존재하지 않습니다."));
    }


}
