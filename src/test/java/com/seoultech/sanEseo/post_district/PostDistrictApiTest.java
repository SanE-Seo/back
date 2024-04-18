package com.seoultech.sanEseo.post_district;

import com.seoultech.sanEseo.ApiTest;
import com.seoultech.sanEseo.district.DistrictSteps;
import com.seoultech.sanEseo.post.PostSteps;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class PostDistrictApiTest extends ApiTest {

    @Test
    void 게시글_자치구_조회(){
        // given

        // 게시글, 자치구 등록
        DistrictSteps.자치구등록요청(DistrictSteps.자치구등록요청_생성());
        DistrictSteps.자치구등록요청(DistrictSteps.자치구등록요청_생성());

        PostSteps.게시글등록요청(PostSteps.게시글등록요청_생성());

        // when
        ExtractableResponse<Response> response = PostDistrictSteps.자치구_게시글_조회(1L);


        // then
        assertThat(response.body().jsonPath().getString("[0].title")).isEqualTo("제목");
        // 개수 확인
        assertThat(response.body().jsonPath().getList("")).hasSize(1);
    }
}
