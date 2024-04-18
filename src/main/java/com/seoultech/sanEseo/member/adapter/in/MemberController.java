package com.seoultech.sanEseo.member.adapter.in;

import com.seoultech.sanEseo.member.adapter.in.dto.AddMemberRequest;
import com.seoultech.sanEseo.member.adapter.in.dto.MemberResponse;
import com.seoultech.sanEseo.member.application.service.MemberService;
import com.seoultech.sanEseo.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<Void> addMember(@RequestBody AddMemberRequest request) {
        memberService.addMember(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponse> findMember(@PathVariable Long id) {
        return ResponseEntity.ok(memberService.loadMember(id));
    }

    @GetMapping("/duplicate")
    public ResponseEntity<Void> checkDuplicateName(@RequestParam String name) {
        memberService.checkDuplicateName(name);
        return ResponseEntity.ok().build();
    }
}
