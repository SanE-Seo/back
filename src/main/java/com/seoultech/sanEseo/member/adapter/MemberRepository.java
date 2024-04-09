package com.seoultech.sanEseo.member.adapter;

import com.seoultech.sanEseo.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
