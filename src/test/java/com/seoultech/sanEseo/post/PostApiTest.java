package com.seoultech.sanEseo.post;

import com.seoultech.sanEseo.ApiTest;
import com.seoultech.sanEseo.district.DistrictSteps;
import com.seoultech.sanEseo.post.adapter.PostRepository;
import com.seoultech.sanEseo.post.application.service.AddPostRequest;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

import static com.seoultech.sanEseo.post.PostSteps.게시글수정요청_생성;
import static org.assertj.core.api.Assertions.assertThat;

@Transactional
public class PostApiTest extends ApiTest {


    @Autowired
    private PostRepository postRepository;

    @Test
    void 게시글등록() {
        final AddPostRequest request = PostSteps.게시글등록요청_생성();

        // API 요청
        final ExtractableResponse<Response> response = PostSteps.게시글등록요청(request);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }


    @Test
    void 게시글조회(){
        DistrictSteps.자치구등록요청(DistrictSteps.자치구등록요청_생성());
        DistrictSteps.자치구등록요청(DistrictSteps.자치구등록요청_생성());

        final var request = PostSteps.게시글등록요청_생성();
        PostSteps.게시글등록요청(request);

        Long postId = 1L;

        ExtractableResponse<Response> response = PostSteps.게시글조회요청(postId);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.body().jsonPath().getString("title")).isEqualTo("제목");

    }

    @Test
    void 게시글수정(){
        DistrictSteps.자치구등록요청(DistrictSteps.자치구등록요청_생성());
        DistrictSteps.자치구등록요청(DistrictSteps.자치구등록요청_생성());

        final var request = PostSteps.게시글등록요청_생성();
        PostSteps.게시글등록요청(request);

        Long postId = 1L;

        ExtractableResponse<Response> response = PostSteps.게시글수정요청(postId, 게시글수정요청_생성());

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(postRepository.findById(1L).get().getTitle()).isEqualTo("수정된 제목");
    }

    @Test
    void 게시글삭제() {
        final var request = PostSteps.게시글등록요청_생성();
        PostSteps.게시글등록요청(request);

        Long postId = 1L;

        ExtractableResponse<Response> response = PostSteps.게시글삭제요청(postId);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }



}
