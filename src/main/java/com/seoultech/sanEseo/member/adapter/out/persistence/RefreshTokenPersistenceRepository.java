package com.seoultech.sanEseo.member.adapter.out.persistence;

import com.seoultech.sanEseo.member.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenPersistenceRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByRefreshToken(String refreshToken);

    Optional<RefreshToken> findByMemberId(Long memberId);

    void deleteByMemberId(Long memberId);
}
