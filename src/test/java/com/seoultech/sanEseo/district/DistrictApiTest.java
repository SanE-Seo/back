package com.seoultech.sanEseo.district;

import com.seoultech.sanEseo.ApiTest;
import com.seoultech.sanEseo.post.adapter.PostRepository;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static com.seoultech.sanEseo.district.DistrictSteps.자치구등록요청;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DistrictApiTest extends ApiTest {

    @Autowired
    private DistrictRepository districtRepository;

    @Test
    void 자치구생성() {
        ExtractableResponse<Response> response = 자치구등록요청(DistrictSteps.자치구등록요청_생성());

        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(districtRepository.findById(1L).get().getName()).isEqualTo("강남구");
    }

    @Test
    void 자치구조회() {
        final var request = DistrictSteps.자치구등록요청_생성();
        자치구등록요청(request);

        ExtractableResponse<Response> response = DistrictSteps.자치구조회요청();

        System.out.println("Returned JSON: " + response.body().asString()); // 로그 추가


        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.body().jsonPath().getString("[0].districtName")).isEqualTo("강남구"); // JSON Path 검토 필요
    }







}
