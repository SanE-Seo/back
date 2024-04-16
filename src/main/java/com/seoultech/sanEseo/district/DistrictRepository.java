package com.seoultech.sanEseo.district;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class DistrictRepository {

    private final Map<String, District> persistence = new HashMap<>();
    private Long id = 0L;

    public void save(District district) {
        district.assignId(++id);
        persistence.put(district.getName(), district);
    }
}
