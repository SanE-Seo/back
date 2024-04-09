package com.seoultech.sanEseo.member.application.port;

import com.seoultech.sanEseo.member.domain.Member;

public interface MemberPort {
    void save(final Member member);
}
