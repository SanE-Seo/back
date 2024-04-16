package com.seoultech.sanEseo.district;

import com.seoultech.sanEseo.ApiTest;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static com.seoultech.sanEseo.district.DistrictSteps.자치구등록요청;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DistrictApiTest extends ApiTest {

    @Test
    void 자치구생성() {
        ExtractableResponse<Response> response = 자치구등록요청(DistrictSteps.자치구등록요청_생성());

        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }





}
