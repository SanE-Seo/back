package com.seoultech.sanEseo.member.adapter.out.persistence;

import com.seoultech.sanEseo.member.application.port.out.RefreshTokenPort;
import com.seoultech.sanEseo.member.domain.RefreshToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RefreshTokenCacheAdapter implements RefreshTokenPort {

    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public void save(RefreshToken refreshToken) {
        refreshTokenRepository.save(refreshToken);
    }

    @Override
    public RefreshToken loadByRefreshToken(String refreshToken) {
        return refreshTokenRepository.findByRefreshToken(refreshToken);
    }

    @Override
    public RefreshToken loadByUserId(String userId) {
        return refreshTokenRepository.findByUserId(userId);
    }
}
