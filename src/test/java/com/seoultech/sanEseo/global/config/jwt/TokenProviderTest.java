package com.seoultech.sanEseo.global.config.jwt;

import com.seoultech.sanEseo.global.exception.InvalidJwtException;
import com.seoultech.sanEseo.global.property.JwtProperties;
import com.seoultech.sanEseo.member.adapter.out.persistence.MemberRepository;
import com.seoultech.sanEseo.member.domain.Member;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Duration;
import java.util.Date;
import java.util.Map;

@SpringBootTest
public class TokenProviderTest {
    @Autowired
    private TokenProvider tokenProvider;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private JwtProperties jwtProperties;

    @Test
    void 토큰생성() {
        Member member = memberRepository.save(Member.builder()
                .email("hongmuchae@gmail.com")
                .name("사용자이름")
                .password("1q2w3e4r!!")
                .build()
        );

        System.out.println(jwtProperties.getSecretKey());

        String token = tokenProvider.generateToken(member, Duration.ofDays(14));

        Key key = Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getBytes(StandardCharsets.UTF_8));

        JwtParser parser = Jwts.parserBuilder()
                .setSigningKey(key)
                .build();

        String name = parser.parseClaimsJws(token).getBody().get("name", String.class);

        Assertions.assertThat(name).isEqualTo(member.getName());
    }

    @Test
    void 만료토큰검증() {
        String token = JwtFactory.builder()
                .expiration(new Date(new Date().getTime() - Duration.ofDays(7).toMillis()))
                .build()
                .createToken(jwtProperties);

        try {
            tokenProvider.validToken(token);
        } catch (Exception e) {
            Assertions.assertThat(e).isInstanceOf(InvalidJwtException.class);
        }
    }

    @Test
    void 유효토큰검증() {
        String token = JwtFactory.withDefaultValues()
                .createToken(jwtProperties);

        boolean result = tokenProvider.validToken(token);

        Assertions.assertThat(result).isTrue();
    }

    @Test
    void 인증정보() {
        String email = "test@gmail.com";
        String token = JwtFactory.builder()
                .subject(email)
                .build()
                .createToken(jwtProperties);

        Authentication authentication = tokenProvider.getAuthentication(token);

        Assertions.assertThat(((UserDetails) authentication.getPrincipal()).getUsername())
                .isEqualTo(email);
    }

    @Test
    void 사용자이름() {
        String name = "사용자이름";
        String token = JwtFactory.builder()
                .claims(Map.of("name", name))
                .build()
                .createToken(jwtProperties);

        String nameByToken = tokenProvider.getUserName(token);

        Assertions.assertThat(nameByToken).isEqualTo(name);
    }
}
