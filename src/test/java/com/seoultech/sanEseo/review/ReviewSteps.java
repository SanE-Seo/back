package com.seoultech.sanEseo.review;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

import java.time.LocalDateTime;

public class ReviewSteps {
    public static CreateReviewRequest 리뷰등록요청_생성() {
        Long memberId = 1L;
        Long postId = 1L;
        String content = "리뷰 내용";
        LocalDateTime now = LocalDateTime.now();
        CreateReviewRequest request = new CreateReviewRequest(memberId, postId, content, now);
        return request;
    }

    public static ExtractableResponse<Response> 리뷰등록요청(CreateReviewRequest request) {
        return RestAssured.given().log().all()
                .contentType("application/json")
                .body(request)
                .when().post("/api/reviews")
                .then().log().all().extract();
    }
}
