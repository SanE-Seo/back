package com.seoultech.sanEseo;

import com.seoultech.sanEseo.post.application.service.AddPostRequest;
import com.seoultech.sanEseo.post.domain.Category;
import com.seoultech.sanEseo.post.domain.PostImage;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PostApiTest extends ApiTest{


    @Test
    void 게시글등록() {
        final AddPostRequest request = 게시글등록요청_생성();

        // API 요청
        final ExtractableResponse<Response> response = 게시글등록요청(request);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    private static ExtractableResponse<Response> 게시글등록요청(AddPostRequest request) {
        return RestAssured.given().log().all()
                .contentType("application/json")
                .body(request)
                .when().post("/api/posts")
                .then().log().all()
                .extract();
    }

    private static AddPostRequest 게시글등록요청_생성() {
        final Category category = Category.DUDREAM;
        final String title = "제목";
        final String subTitle = "부제목";
        final String description = "코스설명";
        final int level = 1;
        final String time = "소요시간";
        final List<PostImage> images = Arrays.asList(new PostImage("이미지1_URL"), new PostImage("이미지2_URL"));

        final AddPostRequest request = new AddPostRequest(category, title, subTitle, description, level, time, images);
        return request;
    }


}
