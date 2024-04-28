package com.seoultech.sanEseo.member.application.port.out;

import com.seoultech.sanEseo.member.domain.RefreshToken;

public interface RefreshTokenPort {
    void save(final RefreshToken refreshToken);

    RefreshToken loadByRefreshToken(final String refreshToken);
    RefreshToken loadByUserId(final Long userId);

    void deleteByUserId(final Long userId);
}
