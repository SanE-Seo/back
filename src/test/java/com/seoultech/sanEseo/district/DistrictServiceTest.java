package com.seoultech.sanEseo.district;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class DistrictServiceTest {

    @Autowired
    private DistrictService districtService;

    @Test
    void 자치구전체조회(){

        DistrictSteps.자치구등록요청(DistrictSteps.자치구등록요청_생성());


        ResponseEntity<List<GetDistrictResponse>> allDistricts = districtService.findAllDistricts();

        assertThat(allDistricts.getStatusCode()).isEqualTo(HttpStatus.OK);

    }
}
