package com.seoultech.sanEseo.district;

import org.springframework.util.Assert;

public record CreateDistrictRequest(Long districtId, String districtName) {
    public CreateDistrictRequest {
        Assert.notNull(districtName, "districtName must not be null");
    }
}
