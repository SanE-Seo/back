package com.seoultech.sanEseo.district;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class DistrictServiceTest {

    @Autowired
    private DistrictService districtService;

    @Test
    void 자치구생성() {
        // given
        final CreateDistrictRequest request = 자치구등록요청_생성();

        districtService.createDistrict(request);


        // then
        assertThat(request.districtName()).isEqualTo("강남구");
    }

    private static CreateDistrictRequest 자치구등록요청_생성() {
        Long districtId = 1L;
        String districtName = "강남구";
        return new CreateDistrictRequest(districtId, districtName);
    }


}
