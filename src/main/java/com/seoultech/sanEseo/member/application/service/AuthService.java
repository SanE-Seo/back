package com.seoultech.sanEseo.member.application.service;

import com.seoultech.sanEseo.member.application.port.in.AuthUseCase;
import com.seoultech.sanEseo.member.application.port.in.OAuthUseCase;
import com.seoultech.sanEseo.member.application.port.in.command.LoginCommand;
import com.seoultech.sanEseo.member.application.port.in.command.EmailRegisterCommand;
import com.seoultech.sanEseo.member.application.port.in.command.ProviderRegisterCommand;
import com.seoultech.sanEseo.member.domain.AccessRefreshToken;
import com.seoultech.sanEseo.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService implements AuthUseCase, OAuthUseCase {
    private final MemberService memberService;
    private final TokenService tokenService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void register(EmailRegisterCommand command) {
        memberService.checkDuplicateEmail(command.getEmail());
        memberService.checkDuplicateName(command.getName());

        memberService.addMember(Member.builder()
                .email(command.getEmail())
                .name(command.getName())
                .password(bCryptPasswordEncoder.encode(command.getPassword())) // 비밀번호 암호화
                .build());
    }



    @Override
    public AccessRefreshToken login(LoginCommand loginCommand) {

        Member member = memberService.loadMemberByEmail(loginCommand.getEmail());

        if(!bCryptPasswordEncoder.matches(loginCommand.getPassword(), member.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return new AccessRefreshToken(
                tokenService.createAccessToken(member),
                tokenService.createRefreshToken(member)
        );
    }

    @Override
    public void register(ProviderRegisterCommand command) {
        memberService.checkDuplicateEmail(command.getEmail());

        memberService.addMember(Member.builder()
                .email(command.getEmail())
                .provider(command.getProvider())
                .build());
    }
}
