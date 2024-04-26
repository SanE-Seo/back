package com.seoultech.sanEseo.member.application.port.in.command;

import com.seoultech.sanEseo.global.common.SelfValidating;
import com.seoultech.sanEseo.member.domain.Provider;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class ProviderRegisterCommand extends SelfValidating<ProviderRegisterCommand> {

    @Email
    @NotBlank
    private final String email;

    @NotBlank
    private final String name;

    private final Provider provider;

    @Builder
    public ProviderRegisterCommand(String email, String name, Provider provider) {
        this.email = email;
        this.name = name;
        this.provider = provider;
        validateSelf();
    }


}
