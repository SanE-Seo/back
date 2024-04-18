package com.seoultech.sanEseo.like;

import com.seoultech.sanEseo.ApiTest;
import com.seoultech.sanEseo.district.DistrictSteps;
import com.seoultech.sanEseo.member.MemberSteps;
import com.seoultech.sanEseo.post.PostSteps;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LikesApiTest extends ApiTest {

    @Test
    void 좋아요등록(){
        // given
        DistrictSteps.자치구등록요청(DistrictSteps.자치구등록요청_생성());
        DistrictSteps.자치구등록요청(DistrictSteps.자치구등록요청_생성());
        PostSteps.게시글등록요청(PostSteps.게시글등록요청_생성());
        MemberSteps.사용자등록요청(MemberSteps.사용자등록요청_생성());

        // when
        ExtractableResponse<Response> response = LikeSteps.좋아요등록요청(LikeSteps.좋아요등록요청_생성());

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());

    }

    @Test
    void 좋아요삭제(){
        DistrictSteps.자치구등록요청(DistrictSteps.자치구등록요청_생성());
        DistrictSteps.자치구등록요청(DistrictSteps.자치구등록요청_생성());
        PostSteps.게시글등록요청(PostSteps.게시글등록요청_생성());
        MemberSteps.사용자등록요청(MemberSteps.사용자등록요청_생성());

        // when
        ExtractableResponse<Response> response = LikeSteps.좋아요등록요청(LikeSteps.좋아요등록요청_생성());
        LikeSteps.좋아요삭제요청(1L);

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    void 게시글_좋아요개수_조회(){
        DistrictSteps.자치구등록요청(DistrictSteps.자치구등록요청_생성());
        DistrictSteps.자치구등록요청(DistrictSteps.자치구등록요청_생성());
        PostSteps.게시글등록요청(PostSteps.게시글등록요청_생성());
        MemberSteps.사용자등록요청(MemberSteps.사용자등록요청_생성());

        // when
        LikeSteps.좋아요등록요청(LikeSteps.좋아요등록요청_생성());

        ExtractableResponse<Response> response = LikeSteps.좋아요개수조회요청(1L);


        // then
        assertThat(response.body().jsonPath().getInt("likeCnt")).isEqualTo(1);

    }


}
