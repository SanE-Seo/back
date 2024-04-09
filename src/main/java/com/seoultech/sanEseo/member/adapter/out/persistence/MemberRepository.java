package com.seoultech.sanEseo.member.adapter.out.persistence;

import com.seoultech.sanEseo.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByName(String name);
}
