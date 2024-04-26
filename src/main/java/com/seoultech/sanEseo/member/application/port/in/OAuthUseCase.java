package com.seoultech.sanEseo.member.application.port.in;

import com.seoultech.sanEseo.member.application.port.in.command.ProviderRegisterCommand;
import org.springframework.stereotype.Component;

@Component
public interface OAuthUseCase {
    void register(ProviderRegisterCommand providerRegisterCommand);
}
