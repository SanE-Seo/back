package com.seoultech.sanEseo.member.adapter.in.web.dto;

import com.seoultech.sanEseo.member.application.port.in.command.LoginCommand;
import org.springframework.util.Assert;

public record LoginRequest(String email, String password) {

    public LoginCommand toCommand() {
        return new LoginCommand(email, password);
    }
}