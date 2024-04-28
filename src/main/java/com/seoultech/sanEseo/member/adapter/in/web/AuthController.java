package com.seoultech.sanEseo.member.adapter.in.web;

import com.seoultech.sanEseo.global.config.web.AuthMember;
import com.seoultech.sanEseo.global.config.web.LoginMember;
import com.seoultech.sanEseo.global.response.ApiResponse;
import com.seoultech.sanEseo.member.adapter.in.web.dto.LoginRequest;
import com.seoultech.sanEseo.member.adapter.in.web.dto.RefreshRequest;
import com.seoultech.sanEseo.member.application.port.in.command.LoginCommand;
import com.seoultech.sanEseo.member.application.service.AuthService;
import com.seoultech.sanEseo.member.application.service.TokenService;
import com.seoultech.sanEseo.member.domain.AccessToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        return ApiResponse.ok("로그인 성공", authService.login(request.toCommand()));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@LoginMember AuthMember authMember) {
        authService.logout(authMember.getEmail());
        return ApiResponse.ok("로그아웃 성공");
    } // TODO : 로그아웃 구현

    @GetMapping("/token/validate")
    public ResponseEntity<?> validateAccessToken() {
        return ApiResponse.ok("인증 성공");
    }

    @GetMapping("/token/refresh")
    public ResponseEntity<?> refresh(@RequestBody RefreshRequest request) {
        System.out.println(request.refreshToken());
        String accessToken = tokenService.refresh(request.refreshToken());
        return ApiResponse.ok("토큰 재발급 성공", new AccessToken(accessToken));
    }

}
