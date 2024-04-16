package com.seoultech.sanEseo.district;

import org.springframework.stereotype.Component;

@Component
public class DistrictService {
    private final DistrictPort districtPort = new DistrictAdapter(new DistrictRepository());

    public void createDistrict(CreateDistrictRequest request) {
        final District district = new District(request.districtId(), request.districtName());
        districtPort.save(district);
    }
}
