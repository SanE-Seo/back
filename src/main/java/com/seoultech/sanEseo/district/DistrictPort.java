package com.seoultech.sanEseo.district;

import java.util.List;

interface DistrictPort {
    void save(District district);

    List<District> findAll();

}
