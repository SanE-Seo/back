package com.seoultech.sanEseo.review;

import com.seoultech.sanEseo.ApiTest;
import com.seoultech.sanEseo.district.DistrictSteps;
import com.seoultech.sanEseo.member.MemberSteps;
import com.seoultech.sanEseo.post.PostSteps;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ReviewApiTest extends ApiTest {

    @Test
    void 리뷰등록() {

        DistrictSteps.자치구등록요청(DistrictSteps.자치구등록요청_생성());
        DistrictSteps.자치구등록요청(DistrictSteps.자치구등록요청_생성());
        PostSteps.게시글등록요청(PostSteps.게시글등록요청_생성());
        MemberSteps.사용자등록요청(MemberSteps.사용자등록요청_생성());

        ExtractableResponse<Response> response = ReviewSteps.리뷰등록요청(ReviewSteps.리뷰등록요청_생성());

        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());

    }

    @Test
    void 리뷰삭제() {

        Long reviewId = 1L;

        DistrictSteps.자치구등록요청(DistrictSteps.자치구등록요청_생성());
        DistrictSteps.자치구등록요청(DistrictSteps.자치구등록요청_생성());
        PostSteps.게시글등록요청(PostSteps.게시글등록요청_생성());
        MemberSteps.사용자등록요청(MemberSteps.사용자등록요청_생성());
        ReviewSteps.리뷰등록요청(ReviewSteps.리뷰등록요청_생성());

        ExtractableResponse<Response> response = ReviewSteps.리뷰삭제요청(reviewId);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.NO_CONTENT.value());

    }

    @Test
    void 리뷰수정(){
        Long reviewId = 1L;
        DistrictSteps.자치구등록요청(DistrictSteps.자치구등록요청_생성());
        DistrictSteps.자치구등록요청(DistrictSteps.자치구등록요청_생성());
        PostSteps.게시글등록요청(PostSteps.게시글등록요청_생성());
        MemberSteps.사용자등록요청(MemberSteps.사용자등록요청_생성());
        ReviewSteps.리뷰등록요청(ReviewSteps.리뷰등록요청_생성());
        ExtractableResponse<Response> response = ReviewSteps.리뷰수정요청(reviewId, ReviewSteps.리뷰수정요청_생성());


        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void 게시글_리뷰조회(){
        Long postId = 2L;
        DistrictSteps.자치구등록요청(DistrictSteps.자치구등록요청_생성());
        DistrictSteps.자치구등록요청(DistrictSteps.자치구등록요청_생성());
        PostSteps.게시글등록요청(PostSteps.게시글등록요청_생성());
        MemberSteps.사용자등록요청(MemberSteps.사용자등록요청_생성());
        ReviewSteps.리뷰등록요청(ReviewSteps.리뷰등록요청_생성());


        ExtractableResponse<Response> response = ReviewSteps.게시글_리뷰조회요청(postId);


        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());}

}

