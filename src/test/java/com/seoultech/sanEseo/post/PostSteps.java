package com.seoultech.sanEseo.post;

import com.seoultech.sanEseo.post.application.service.AddPostRequest;
import com.seoultech.sanEseo.post.application.service.UpdatePostRequest;
import com.seoultech.sanEseo.post.domain.Category;
import com.seoultech.sanEseo.public_api.domain.Coordinate;
import com.seoultech.sanEseo.image.PostImage;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

import java.util.Arrays;
import java.util.List;

public class PostSteps {
    public static ExtractableResponse<Response> 게시글등록요청(AddPostRequest request) {
        return RestAssured.given().log().all()
                .contentType("application/json")
                .body(request)
                .when().post("/api/posts")
                .then().log().all()
                .extract();
    }

    public static AddPostRequest 게시글등록요청_생성() {
        final Category category = Category.DUDREAM;
        final String title = "제목";
        final String subTitle = "부제목";
        final String description = "코스설명";
        final String level = "초급";
        final String time = "소요시간";
        final String distance = "1.45";
        final String courseDetail = "코스상세";
        final String transportation = "교통수단";
        final Coordinate coordinate = new Coordinate("한양도성길", 37.1234, 127.1234, "(4,89,127.013600598,37.526797483,127.116737933,37.562810756,0,0,0,0,0,0.138038272495688,12,oracle.sql.BLOB@1800d8)");
        final List<PostImage> images = Arrays.asList(new PostImage("이미지1_URL"), new PostImage("이미지2_URL"));

        final AddPostRequest request = new AddPostRequest(category, title, subTitle, description, level, time, distance, courseDetail, transportation, coordinate, images, List.of(1L, 2L));
        return request;
    }

    static ExtractableResponse<Response> 게시글조회요청(Long postId) {
        return RestAssured.given().log().all()
                .when().get("/api/posts/{postId}", postId)
                .then().log().all()
                .extract();
    }

    static UpdatePostRequest 게시글수정요청_생성() {
        return new UpdatePostRequest(Category.CUSTOM, "수정된 제목", "수정된 부제목", "수정된 내용", "초급", "소요시간", "1.45", "코스상세", "교통수단", new Coordinate("수정된 좌표", 37.1234, 127.1234, "(4,89,127.013600598,37.526797483,127.116737933,37.562810756,0,0,0,0,0,0.138038272495688,12,oracle.sql.BLOB@1800d8)"), Arrays.asList(new PostImage("수정된 이미지1_URL"), new PostImage("수정된 이미지2_URL")), List.of(1L, 2L));
    }

    public static ExtractableResponse<Response> 게시글수정요청(Long postId, UpdatePostRequest updateRequest) {
        return RestAssured.given().log().all()
                .contentType("application/json")
                .body(updateRequest)
                .when().put("/api/posts/{postId}", postId)
                .then().log().all().extract();
    }

    public static ExtractableResponse<Response> 게시글삭제요청(Long postId) {
        return RestAssured.given().log().all()
                .when().delete("/api/posts/{postId}", postId)
                .then().log().all().extract();
    }


}