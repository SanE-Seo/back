package com.seoultech.sanEseo.member.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AccessRefreshToken {
    private String accessToken;
    private String refreshToken;
}
