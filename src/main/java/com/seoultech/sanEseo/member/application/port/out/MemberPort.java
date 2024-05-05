package com.seoultech.sanEseo.member.application.port.out;

import com.seoultech.sanEseo.member.domain.Member;
import com.seoultech.sanEseo.post.domain.Post;

import java.util.List;
import java.util.Optional;

public interface MemberPort {
    Member save(final Member member);

    Member loadById(final Long id);
    Member loadByEmail(final String email);

    boolean existsByName(final String name);

    boolean existsByEmail(final String email);

    long getNewIndex();

}
