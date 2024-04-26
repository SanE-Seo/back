package com.seoultech.sanEseo.member.application.service;

import com.seoultech.sanEseo.member.adapter.in.web.dto.MemberResponse;
import com.seoultech.sanEseo.member.application.port.out.MemberPort;
import com.seoultech.sanEseo.member.domain.Member;
import com.seoultech.sanEseo.member.exception.DuplicateEmailException;
import com.seoultech.sanEseo.member.exception.DuplicateNameException;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberPort memberPort;

    public MemberService(MemberPort memberPort) {
        this.memberPort = memberPort;
    }

    public void addMember(Member member) {
        // DTO -> Entity
        memberPort.save(member);
    }

    public Member loadMember(Long id) {
        // Entity -> DTO
        return memberPort.loadById(id);
    }

    public Member loadMemberByEmail(String email) {
        return memberPort.loadByEmail(email);
    }


    public void checkDuplicateName(String name) {
        if(memberPort.existsByName(name)) {
            throw new DuplicateNameException("이미 존재하는 이름입니다.");
        }
    }

    public void checkDuplicateEmail(String email) {
        if(memberPort.existsByEmail(email)) {
            throw new DuplicateEmailException("이미 존재하는 이메일입니다.");
        }
    }

    public String generateName() {
        return "서울#" + memberPort.getNewIndex();
    }
}
