package com.seoultech.sanEseo;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

public class PostDistrictSteps {


    public static ExtractableResponse<Response> 자치구_게시글_조회(Long districtId) {
        return RestAssured.given().log().all()
                .when().get("/api/districts/{districtId}/posts", districtId)
                .then().log().all()
                .extract();
    }
}
