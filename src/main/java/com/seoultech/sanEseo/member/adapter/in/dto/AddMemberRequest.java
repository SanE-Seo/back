package com.seoultech.sanEseo.member.adapter.in.dto;

import com.seoultech.sanEseo.member.domain.Member;
import org.springframework.util.Assert;

public record AddMemberRequest(String userId, String name, String email, String password, String profile) {
    public AddMemberRequest {
        Assert.hasText(userId, "아이디는 필수입니다.");
        Assert.hasText(name, "사용자명은 필수입니다.");
        Assert.hasText(email, "이메일은 필수입니다.");
        Assert.hasText(password, "비밀번호는 필수입니다.");
        Assert.hasText(profile, "프로필은 필수입니다.");
    }

    public Member toEntity() {
        return Member.builder()
                .userId(userId)
                .name(name)
                .email(email)
                .password(password)
                .profile(profile)
                .build();
    }
}
