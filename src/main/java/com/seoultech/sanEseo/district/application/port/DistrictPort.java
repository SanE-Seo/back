package com.seoultech.sanEseo.district.application.port;

import com.seoultech.sanEseo.district.domain.District;

import java.util.List;

public interface DistrictPort {
    void save(District district);

    List<District> findAll();

    void delete(Long districtId);

    District findById(Long districtId);

}
