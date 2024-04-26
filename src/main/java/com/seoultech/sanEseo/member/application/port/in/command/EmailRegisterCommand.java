package com.seoultech.sanEseo.member.application.port.in.command;

import com.seoultech.sanEseo.global.common.SelfValidating;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class EmailRegisterCommand extends SelfValidating<EmailRegisterCommand> {

    @Email
    @NotBlank
    private final String email;

    @NotBlank
    private final String name;

    @NotBlank
    private final String password;

    @Builder
    public EmailRegisterCommand(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
        validateSelf();
    }
}
