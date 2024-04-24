package com.seoultech.sanEseo.member.adapter.out.persistence;

import com.seoultech.sanEseo.member.application.port.out.RefreshTokenPort;
import com.seoultech.sanEseo.member.domain.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, Long> {
    RefreshToken findByRefreshToken(String refreshToken);
    RefreshToken findByUserId(String userId);
}
