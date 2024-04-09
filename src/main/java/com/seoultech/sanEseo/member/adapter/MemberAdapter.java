package com.seoultech.sanEseo.member.adapter;

import com.seoultech.sanEseo.member.application.port.MemberPort;
import com.seoultech.sanEseo.member.domain.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberAdapter implements MemberPort {

    private final MemberRepository memberRepository;

    public MemberAdapter(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void save(Member member) {
        memberRepository.save(member);
    }
}
