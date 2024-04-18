package com.seoultech.sanEseo.district;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

public class DistrictSteps {

    private static long districtCounter = 1L;

    public static ExtractableResponse<Response> 자치구등록요청(CreateDistrictRequest request) {
        return RestAssured.given().log().all()
                .contentType("application/json")
                .body(request)
                .when().post("/api/districts")
                .then().log().all()
                .extract();
    }

    public static CreateDistrictRequest 자치구등록요청_생성() {
        long districtId = districtCounter++;
        String districtName = "강남구";

        return new CreateDistrictRequest(districtId, districtName);
    }

    public static ExtractableResponse<Response> 자치구조회요청() {
        return RestAssured.given().log().all()
                .contentType("application/json")
                .when().get("/api/districts")
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 자치구삭제요청(Long districtId) {
        return RestAssured.given().log().all()
                .contentType("application/json")
                .when().delete("/api/districts/" + districtId)
                .then().log().all()
                .extract();
    }
}
