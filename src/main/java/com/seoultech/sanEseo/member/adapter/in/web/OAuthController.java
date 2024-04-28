package com.seoultech.sanEseo.member.adapter.in.web;

import com.seoultech.sanEseo.global.response.ApiResponse;
import com.seoultech.sanEseo.member.application.service.KakaoService;
import com.seoultech.sanEseo.member.domain.AccessRefreshToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/oauth")
public class OAuthController {

    private final KakaoService kakaoService;

    @GetMapping("/kakao")
    public ResponseEntity<?> kakaoLogin(@RequestParam String code) {
        return ApiResponse.ok("카카오 로그인 성공", kakaoService.login(code));
    }
}
