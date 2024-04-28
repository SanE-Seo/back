package com.seoultech.sanEseo.member.application.service;

import com.seoultech.sanEseo.global.config.jwt.TokenProvider;
import com.seoultech.sanEseo.global.property.JwtProperties;
import com.seoultech.sanEseo.member.application.port.out.MemberPort;
import com.seoultech.sanEseo.member.application.port.out.RefreshTokenPort;
import com.seoultech.sanEseo.member.domain.Member;
import com.seoultech.sanEseo.member.domain.RefreshToken;
import jakarta.transaction.Transactional;
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
    private final JwtProperties jwtProperties;

    public String refresh(String refreshToken) {

        tokenProvider.validateRefreshToken(refreshToken);

        Long memberId = refreshTokenPort.loadByRefreshToken(refreshToken).getMemberId();
        Member member = memberPort.loadById(memberId);

        return createAccessToken(member);
    }

    public String createAccessToken(Member member) {
        return tokenProvider.generateToken(member, Duration.ofMinutes(jwtProperties.getAccessExpiredMin()));
    }

    @Transactional
    public String createRefreshToken(Member member) {
        String token =  tokenProvider.generateToken(member, Duration.ofDays(jwtProperties.getRefreshExpiredDay()));

        RefreshToken refreshToken = refreshTokenPort.loadByUserId(member.getId());
        if(refreshToken == null) {
            refreshToken = RefreshToken.builder()
                    .memberId(member.getId())
                    .refreshToken(token)
                    .build();
        } else {
            refreshToken.setRefreshToken(token);
        }

        refreshTokenPort.save(refreshToken);

        return token;
    }
}
