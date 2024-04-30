package com.seoultech.sanEseo.global.config.jwt;

import com.seoultech.sanEseo.global.exception.InvalidJwtException;
import com.seoultech.sanEseo.global.property.JwtProperties;
import com.seoultech.sanEseo.member.domain.Member;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class TokenProvider {
    private final JwtProperties jwtProperties;

    public String generateToken(Member member, Duration expiredAt) {
        Date now = new Date();
        return makeToken(new Date(now.getTime() + expiredAt.toMillis()), member);
    }

    // TODO: 사용자 정보 페이로드에 추가
    private String makeToken(Date expiry, Member member) {
        Date now = new Date();

        Key key = Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .setSubject(member.getEmail())
                .claim("name", member.getName())
                .claim("email", member.getEmail())
                .claim("id", member.getId())
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validToken(String token) {
        try {
            Key key = Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getBytes(StandardCharsets.UTF_8));

            JwtParser parser = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build();

            parser.parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException | io.jsonwebtoken.security.SignatureException e){
            throw new InvalidJwtException("잘못된 JWT 서명");
        } catch (UnsupportedJwtException e){
            throw new InvalidJwtException("지원하지 않는 JWT 토큰");
        } catch (IllegalArgumentException e){
            throw new InvalidJwtException("잘못된 토큰 값");
        } catch (ExpiredJwtException e) {
            throw new InvalidJwtException("만료된 JWT 토큰입니다");
        }
    }

    public boolean validateRefreshToken(String refreshToken){
        try{
            Jwts.parserBuilder().setSigningKey(jwtProperties.getSecretKey().getBytes(StandardCharsets.UTF_8)).build().parse(refreshToken);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            throw new InvalidJwtException("잘못된 리프레시 토큰입니다");
        }
    }

    public Authentication getAuthentication(String token) {
        Claims claims = getClaims(token);
        Set<SimpleGrantedAuthority> authorities =
                Collections.singleton(new SimpleGrantedAuthority("ROLE_MEMBER"));

        return new UsernamePasswordAuthenticationToken(
                new User(claims.get("id").toString(), "", authorities), token, authorities
        );
    }

    public String getUserName(String token) {
        Claims claims = getClaims(token);
        return claims.get("name", String.class);
    }

    private Claims getClaims(String token) {
        Key key = Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getBytes(StandardCharsets.UTF_8));

        JwtParser parser = Jwts.parserBuilder()
                .setSigningKey(key)
                .build();

        return parser.parseClaimsJws(token)
                .getBody();
    }
}
