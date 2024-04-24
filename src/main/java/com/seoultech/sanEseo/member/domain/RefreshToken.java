package com.seoultech.sanEseo.member.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Builder
@RedisHash
public class RefreshToken {

    @Id
    private Long id;

    private Long memberId;

    private String refreshToken;
}
