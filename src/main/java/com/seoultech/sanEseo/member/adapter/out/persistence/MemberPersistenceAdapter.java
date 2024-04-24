package com.seoultech.sanEseo.member.adapter.out.persistence;

import com.seoultech.sanEseo.member.application.port.out.MemberPort;
import com.seoultech.sanEseo.member.domain.Member;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class MemberPersistenceAdapter implements MemberPort {

    private final MemberRepository memberRepository;

    public MemberPersistenceAdapter(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void save(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member loadById(Long id) {
        return memberRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("존재하지 않는 회원입니다. : " + id));
    }

    @Override
    public boolean existsByName(String name) {
        return memberRepository.existsByName(name);
    }
}
