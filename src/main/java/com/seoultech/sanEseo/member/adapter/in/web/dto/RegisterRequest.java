package com.seoultech.sanEseo.member.adapter.in.web.dto;

import com.seoultech.sanEseo.member.application.port.in.command.EmailRegisterCommand;
import com.seoultech.sanEseo.member.domain.Member;

public record RegisterRequest(String name, String email, String password) {

    public Member toEntity() {
        return Member.builder()
                .name(name)
                .email(email)
                .password(password)
                .build();
    }

    public EmailRegisterCommand toCommand() {
        return EmailRegisterCommand.builder()
                .email(email)
                .name(name)
                .password(password)
                .build();
    }
}
