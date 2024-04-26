package com.seoultech.sanEseo.member.application.port.in.command;

import com.seoultech.sanEseo.global.common.SelfValidating;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class LoginCommand extends SelfValidating<LoginCommand> {

    @Email
    @NotBlank
    private final String email;

    @NotBlank
    private final String password; // TODO: 로그인 비밀번호 정규식 미적용

    public LoginCommand(String email, String password) {
        this.email = email;
        this.password = password;
        validateSelf();
    }
}
