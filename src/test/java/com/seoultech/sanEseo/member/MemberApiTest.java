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


}
