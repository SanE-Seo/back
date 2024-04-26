package com.seoultech.sanEseo.member.adapter.in.web;

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
    public ResponseEntity<?> logout() {
        return null;
    }

    @GetMapping("/token/validate")
    public ResponseEntity<?> validateAccessToken() {
        return ResponseEntity.ok("인증 성공");
    }

    @GetMapping("/token/refresh")
    public ResponseEntity<?> refresh(@RequestBody RefreshRequest request) {
        System.out.println(request.refresh());
        String accessToken = tokenService.refresh(request.refresh());
        return ApiResponse.ok("토큰 재발급 성공", new AccessToken(accessToken));
    }

}
