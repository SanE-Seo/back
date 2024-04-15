package com.seoultech.sanEseo;

import com.seoultech.sanEseo.post.application.service.AddPostRequest;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

public class PostApiTest extends ApiTest{


    @Test
    void 게시글등록() {
        final AddPostRequest request = PostSteps.게시글등록요청_생성();

        // API 요청
        final ExtractableResponse<Response> response = PostSteps.게시글등록요청(request);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }


}
