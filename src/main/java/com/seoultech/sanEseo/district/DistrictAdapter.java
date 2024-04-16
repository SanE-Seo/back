package com.seoultech.sanEseo.district;


import org.springframework.stereotype.Component;

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
}
