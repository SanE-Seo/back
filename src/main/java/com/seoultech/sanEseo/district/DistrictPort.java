package com.seoultech.sanEseo.district;

import java.util.List;

public interface DistrictPort {
    void save(District district);

    List<District> findAll();

    void delete(Long districtId);

    District findById(Long districtId);

}
