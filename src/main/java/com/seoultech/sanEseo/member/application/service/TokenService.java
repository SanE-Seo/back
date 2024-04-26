package com.seoultech.sanEseo.member.application.service;

import com.seoultech.sanEseo.global.config.jwt.TokenProvider;
import com.seoultech.sanEseo.member.application.port.out.MemberPort;
import com.seoultech.sanEseo.member.application.port.out.RefreshTokenPort;
import com.seoultech.sanEseo.member.domain.Member;
import com.seoultech.sanEseo.member.domain.RefreshToken;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.Duration;

@RequiredArgsConstructor
@Service
public class TokenService {
    private final TokenProvider tokenProvider;
    private final RefreshTokenPort refreshTokenPort;
    private final MemberPort memberPort;

    public String refresh(String refreshToken) {

        tokenProvider.validateRefreshToken(refreshToken);

        Long memberId = refreshTokenPort.loadByRefreshToken(refreshToken).getMemberId();
        Member member = memberPort.loadById(memberId);

        // TODO: 토큰 만료시간 통일
        return tokenProvider.generateToken(member, Duration.ofDays(7));
    }

    public String createAccessToken(Member member) {
        return tokenProvider.generateToken(member, Duration.ofMinutes(30));
    }

    public String createRefreshToken(Member member) {
        String token =  tokenProvider.generateToken(member, Duration.ofDays(7));

        refreshTokenPort.save(RefreshToken.builder()
                .memberId(member.getId())
                .refreshToken(token)
                .build()
        );

        return token;
    }
}
