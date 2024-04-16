package com.seoultech.sanEseo.district;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

public class DistrictSteps {
    public static ExtractableResponse<Response> 자치구등록요청(CreateDistrictRequest request) {
        return RestAssured.given().log().all()
                .contentType("application/json")
                .body(request)
                .when().post("/api/districts")
                .then().log().all()
                .extract();
    }

    public static CreateDistrictRequest 자치구등록요청_생성() {
        Long districtId = 1L;
        String districtName = "강남구";

        return new CreateDistrictRequest(districtId, districtName);
    }
}
