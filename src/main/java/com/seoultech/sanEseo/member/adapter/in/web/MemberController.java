package com.seoultech.sanEseo.member.adapter.in.web;

import com.seoultech.sanEseo.global.config.web.AuthMember;
import com.seoultech.sanEseo.global.config.web.LoginMember;
import com.seoultech.sanEseo.global.response.ApiResponse;
import com.seoultech.sanEseo.member.adapter.in.web.dto.RegisterRequest;
import com.seoultech.sanEseo.member.adapter.in.web.dto.MemberResponse;
import com.seoultech.sanEseo.member.adapter.in.web.dto.UpdateMemberRequest;
import com.seoultech.sanEseo.member.application.port.in.AuthUseCase;
import com.seoultech.sanEseo.member.application.port.out.MemberPort;
import com.seoultech.sanEseo.member.application.service.AuthService;
import com.seoultech.sanEseo.member.application.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;
    private final AuthUseCase authUseCase;

    @PostMapping
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        authUseCase.register(request.toCommand());
        return ApiResponse.ok("회원가입 성공");
    }

    @PatchMapping
    public ResponseEntity<?> updateMember(@LoginMember AuthMember authMember, @ModelAttribute UpdateMemberRequest request) {
        return ApiResponse.ok("회원정보 수정 성공", memberService.updateMember(request.toCommand(authMember.getId())));
    }

    @GetMapping
    public ResponseEntity<?> findMember(@LoginMember AuthMember authMember) {
        return ApiResponse.ok("사용자 정보 조회 성공", MemberResponse.fromEntity(memberService.loadMember(authMember.getId())));
    }

    @GetMapping("/duplicate")
    public ResponseEntity<?> checkDuplicateName(@RequestParam String username) {
        memberService.checkDuplicateName(username);
        return ApiResponse.ok(username + "은 사용 가능한 이름입니다.");
    }
}
