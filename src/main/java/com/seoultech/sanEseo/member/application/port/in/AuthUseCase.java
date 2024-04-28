package com.seoultech.sanEseo.member.application.port.in;

import com.seoultech.sanEseo.member.application.port.in.command.LoginCommand;
import com.seoultech.sanEseo.member.application.port.in.command.EmailRegisterCommand;
import com.seoultech.sanEseo.member.domain.AccessRefreshToken;
import org.springframework.stereotype.Component;

@Component
public interface AuthUseCase {

    void register(EmailRegisterCommand registerCommand);
    AccessRefreshToken login(LoginCommand loginCommand);
}
