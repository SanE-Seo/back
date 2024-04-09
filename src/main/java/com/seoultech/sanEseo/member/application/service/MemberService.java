package com.seoultech.sanEseo.member.application.service;

import com.seoultech.sanEseo.member.adapter.in.dto.AddMemberRequest;
import com.seoultech.sanEseo.member.adapter.in.dto.MemberResponse;
import com.seoultech.sanEseo.member.application.port.out.MemberPort;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberPort memberPort;

    public MemberService(MemberPort memberPort) {
        this.memberPort = memberPort;
    }

    public void addMember(AddMemberRequest request) {
        // DTO -> Entity
        memberPort.save(request.toEntity());
    }

    public MemberResponse loadMember(Long id) {
        // Entity -> DTO
        return MemberResponse.fromEntity(memberPort.loadById(id));
    }

    public void checkDuplicateName(String name) {
        if(memberPort.existsByName(name)) {
            throw new IllegalArgumentException("이미 존재하는 이름입니다.");
        }
    }
}
