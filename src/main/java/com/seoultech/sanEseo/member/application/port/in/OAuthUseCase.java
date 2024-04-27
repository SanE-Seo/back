package com.seoultech.sanEseo.member.application.port.in;

import com.seoultech.sanEseo.member.application.port.in.command.OAuthRegisterCommand;
import org.springframework.stereotype.Component;

@Component
public interface OAuthUseCase {
    void register(OAuthRegisterCommand providerRegisterCommand);
}
