package com.seoultech.sanEseo.member.adapter.out.persistence;

import com.seoultech.sanEseo.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByName(String name);

    boolean existsByEmail(String email);

    Optional<Member> findByEmail(String email);

    Optional<Member> findFirstByOrderByIdDesc();
}
