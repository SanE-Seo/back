package com.seoultech.sanEseo.district;

import org.springframework.util.Assert;

import java.util.List;

public record GetDistrictResponse(Long districtId, String districtName) {
    public GetDistrictResponse {
        Assert.notNull(districtId, "지역 ID는 필수입니다.");
        Assert.hasText(districtName, "지역 이름은 필수입니다.");
    }

    public static List<GetDistrictResponse> from(List<District> districts) {
        return districts.stream()
                .map(district -> new GetDistrictResponse(district.getId(), district.getName()))
                .toList();
    }
}
