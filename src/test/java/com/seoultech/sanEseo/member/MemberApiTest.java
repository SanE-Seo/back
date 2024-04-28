package com.seoultech.sanEseo.member;

import com.seoultech.sanEseo.ApiTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MemberApiTest extends ApiTest {
    @Test
    void 사용자등록() {
        final var request = MemberSteps.사용자등록요청_생성();
        final var response = MemberSteps.사용자등록요청(request);

        Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    void 사용자조회() {
        // TODO : 추후 JWT 토큰 기반 인증 변경 필요
        MemberSteps.사용자등록요청(MemberSteps.사용자등록요청_생성());
        Long memberId = 1L;

        final var response = MemberSteps.사용자조회요청(memberId);
        Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        Assertions.assertThat(response.jsonPath().getString("name")).isEqualTo("사용자명");
    }

    @Test
    void 사용자이름중복확인() {
        MemberSteps.사용자등록요청(MemberSteps.사용자등록요청_생성());

        final var response = MemberSteps.사용자이름중복확인요청("없는사용자명");
        Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());

        final var response2 = MemberSteps.사용자이름중복확인요청("사용자명");
        Assertions.assertThat(response2.statusCode()).isNotEqualTo(HttpStatus.OK.value());
    }

}
