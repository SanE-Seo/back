package com.seoultech.sanEseo.district.application.port;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor  // Jackson이 기본 생성자를 사용할 수 있도록 함
@AllArgsConstructor // 모든 필드를 포함하는 생성자 자동 생성
public class CreateDistrictRequest {

    @NotNull(message = "자치구 이름은 필수입니다.")
    String districtName;

}
