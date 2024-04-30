package com.seoultech.sanEseo.like;

import com.seoultech.sanEseo.like.application.service.AddLikeRequest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

public class LikeSteps {
    public static ExtractableResponse<Response> 좋아요등록요청(AddLikeRequest request) {
        return RestAssured.given().log().all()
                .contentType("application/json")
                .body(request)
                .when().post("/api/likes")
                .then().log().all()
                .extract();
    }


    public static AddLikeRequest 좋아요등록요청_생성() {
        return new AddLikeRequest(1L);
    }

    public static ExtractableResponse<Response> 좋아요삭제요청(Long likeId) {
            return RestAssured.given().log().all()
                    .when().delete("/api/likes/{likeId}", likeId)
                    .then().log().all()
                    .extract();
        }

    public static ExtractableResponse<Response> 좋아요개수조회요청(Long postId) {
        return RestAssured.given().log().all()
                .when().get("/api/posts/{postId}/likes", postId)
                .then().log().all()
                .extract();
    }
}
