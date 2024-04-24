package com.seoultech.sanEseo.member.application.port.out;

import com.seoultech.sanEseo.member.domain.Member;

import java.util.Optional;

public interface MemberPort {
    void save(final Member member);

    Member loadById(final Long id);

    boolean existsByName(final String name);
}
