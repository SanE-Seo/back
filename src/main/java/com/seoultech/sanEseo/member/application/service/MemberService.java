package com.seoultech.sanEseo.member.application.service;

import com.seoultech.sanEseo.member.application.port.MemberPort;
import com.seoultech.sanEseo.member.domain.Member;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member")
public class MemberService {

    private final MemberPort memberPort;

    public MemberService(MemberPort memberPort) {
        this.memberPort = memberPort;
    }

    @PostMapping
    public ResponseEntity<Void> addMember(@RequestBody AddMemberRequest request) {
        final Member member = new Member(request.userId(), request.userName(), request.email(), request.password(), request.profile());
        memberPort.save(member);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
