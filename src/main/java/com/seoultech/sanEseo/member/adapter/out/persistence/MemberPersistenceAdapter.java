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
    public Member save(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public Member loadById(Long id) {
        return memberRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("존재하지 않는 회원입니다. memberId: " + id));
    }

    @Override
    public Member loadByEmail(String email) {
        return memberRepository.findByEmail(email).orElseThrow(
                () -> new EntityNotFoundException("해당 이메일을 가진 사용자가 존재하지 않습니다.")
        );
    }

    @Override
    public boolean existsByName(String name) {
        return memberRepository.existsByName(name);
    }

    @Override
    public boolean existsByEmail(String email) {
        return memberRepository.existsByEmail(email);
    }

    @Override
    public long getNewIndex() {
        return memberRepository.findFirstByOrderByIdDesc().orElse(Member.builder()
                .id(0L).build()).getId() + 1;
    }
}
