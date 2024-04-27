package com.seoultech.sanEseo.global.config.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final TokenProvider tokenProvider;
    private final static String HEADER_AUTHORIZATION = "Authorization";
    private final static String TOKEN_PREFIX = "Bearer ";

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        // 요청 주소 출력
        System.out.println("Request URL: " + request.getRequestURI());
        //요청 본문 출력
        System.out.println("Request body: " + request.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual));

        String authorizationHeader = request.getHeader(HEADER_AUTHORIZATION);
        String token = getAccessToken(authorizationHeader);
        if (tokenProvider.validToken(token)) {
            Authentication authentication = tokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication); // 인증
        }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        List<AntPathRequestMatcher> skipPathList = new ArrayList<>();
        skipPathList.add(new AntPathRequestMatcher("/", HttpMethod.GET.name()));
        skipPathList.add(new AntPathRequestMatcher("/favicon.ico**", HttpMethod.GET.name()));
        skipPathList.add(new AntPathRequestMatcher("/api/member", HttpMethod.POST.name()));
        skipPathList.add(new AntPathRequestMatcher("/api/member/duplicate", HttpMethod.GET.name()));
        skipPathList.add(new AntPathRequestMatcher("/api/auth/login", HttpMethod.POST.name()));
        skipPathList.add(new AntPathRequestMatcher("/api/auth/token/refresh", HttpMethod.GET.name()));
        skipPathList.add(new AntPathRequestMatcher("/api/oauth/kakao", HttpMethod.GET.name()));
        skipPathList.add(new AntPathRequestMatcher("/api/weather", HttpMethod.GET.name()));
        skipPathList.add(new AntPathRequestMatcher("/api/posts/**", HttpMethod.GET.name()));
        skipPathList.add(new AntPathRequestMatcher("/h2-console/**"));

        OrRequestMatcher orRequestMatcher = new OrRequestMatcher(new ArrayList<>(skipPathList));
        return skipPathList.stream()
                .anyMatch(p -> orRequestMatcher.matches(request));
    }

    private String getAccessToken(String authorizationHeader) {
        if(authorizationHeader != null && authorizationHeader.startsWith(TOKEN_PREFIX)) {
            return authorizationHeader.substring(TOKEN_PREFIX.length());
        }
        return null;
    }
}
