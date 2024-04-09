package com.seoultech.sanEseo.member.application.service;

import org.springframework.util.Assert;

public record AddMemberRequest(String userId, String userName, String email, String password, String profile) {
    public AddMemberRequest {
        Assert.hasText(userId, "아이디는 필수입니다.");
        Assert.hasText(userName, "사용자명은 필수입니다.");
        Assert.hasText(email, "이메일은 필수입니다.");
        Assert.hasText(password, "비밀번호는 필수입니다.");
        Assert.hasText(profile, "프로필은 필수입니다.");
    }
}
