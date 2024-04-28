package com.seoultech.sanEseo.member.application.service;

import com.seoultech.sanEseo.member.application.port.in.AuthUseCase;
import com.seoultech.sanEseo.member.application.port.in.OAuthUseCase;
import com.seoultech.sanEseo.member.application.port.in.command.LoginCommand;
import com.seoultech.sanEseo.member.application.port.in.command.EmailRegisterCommand;
import com.seoultech.sanEseo.member.application.port.in.command.OAuthRegisterCommand;
import com.seoultech.sanEseo.member.application.port.out.MemberPort;
import com.seoultech.sanEseo.member.application.port.out.RefreshTokenPort;
import com.seoultech.sanEseo.member.domain.AccessRefreshToken;
import com.seoultech.sanEseo.member.domain.Member;
import com.seoultech.sanEseo.member.domain.RefreshToken;
import com.seoultech.sanEseo.member.exception.NotLoginedMemberException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService implements AuthUseCase, OAuthUseCase {
    private final MemberPort memberPort;
    private final RefreshTokenPort refreshTokenPort;
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
    public void register(OAuthRegisterCommand command) {
        memberService.checkDuplicateEmail(command.getEmail());

        memberService.addMember(Member.builder()
                .name(command.getName())
                .email(command.getEmail())
                .provider(command.getProvider())
                .build());
    }

    @Transactional
    public void logout(String email) {
        Member member = memberPort.loadByEmail(email);
        RefreshToken refreshToken = refreshTokenPort.loadByUserId(member.getId());
        if(refreshToken == null) {
            throw new NotLoginedMemberException("로그인이 되어있지 않습니다.");
        }

        refreshTokenPort.deleteByUserId(member.getId());
    }
}
