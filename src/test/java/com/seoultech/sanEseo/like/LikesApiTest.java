package com.seoultech.sanEseo.like;

import com.seoultech.sanEseo.ApiTest;
import com.seoultech.sanEseo.district.DistrictSteps;
import com.seoultech.sanEseo.member.MemberSteps;
import com.seoultech.sanEseo.post.PostSteps;
import io.restassured.RestAssured;
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


    public class LikeSteps {
        public static ExtractableResponse<Response> 좋아요등록요청(AddLikeRequest request) {
            return RestAssured.given().log().all()
                    .contentType("application/json")
                    .body(request)
                    .when().post("/api/likes")
                    .then().log().all()
                    .extract();
        }


        public static AddLikeRequest 좋아요등록요청_생성(){
            return new AddLikeRequest(1L, 1L);
        }
    }
}
