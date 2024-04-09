package com.seoultech.sanEseo.member;

import com.seoultech.sanEseo.member.application.service.AddMemberRequest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

public class MemberSteps {

    public static ExtractableResponse<Response> 사용자등록요청(final AddMemberRequest request) {
        return RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post("/api/member")
                .then()
                .log().all().extract();
    }

    public static AddMemberRequest 사용자등록요청_생성() {
        final String id = "사용자아이디";
        final String name = "사용자명";
        final String email = "이메일";
        final String password = "비밀번호";
        final String profile = "프로필";

        return new AddMemberRequest(id, name, email, password, profile);
    }
}
