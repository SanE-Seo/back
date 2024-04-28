package com.seoultech.sanEseo.member.adapter.out.persistence;

import com.seoultech.sanEseo.global.exception.InvalidJwtException;
import com.seoultech.sanEseo.member.application.port.out.RefreshTokenPort;
import com.seoultech.sanEseo.member.domain.RefreshToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RefreshTokenPersistenceAdapter implements RefreshTokenPort {

    private final RefreshTokenPersistenceRepository refreshTokenPersistenceRepository;

    @Override
    public void save(RefreshToken refreshToken) {
        refreshTokenPersistenceRepository.save(refreshToken);
    }

    @Override
    public RefreshToken loadByRefreshToken(String refreshToken) {
        return refreshTokenPersistenceRepository.findByRefreshToken(refreshToken).orElseThrow(() ->
                new InvalidJwtException("토큰이 유효하지 않습니다."));
    }

    @Override
    public RefreshToken loadByUserId(Long userId) {
        return refreshTokenPersistenceRepository.findByMemberId(userId).orElse(null);
    }

    @Override
    public void deleteByUserId(Long userId) {
        refreshTokenPersistenceRepository.deleteByMemberId(userId);
    }
}
