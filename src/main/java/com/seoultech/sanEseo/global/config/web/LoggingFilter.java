package com.seoultech.sanEseo.global.config.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seoultech.sanEseo.global.common.AccessLog;
import com.seoultech.sanEseo.global.common.RequestLogUtil;
import com.seoultech.sanEseo.global.common.RequestWrapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
public class LoggingFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        RequestWrapper requestWrapper = new RequestWrapper(request);
        AccessLog logging = logging(requestWrapper);
        log.info(objectMapper.writeValueAsString(logging));
        filterChain.doFilter(requestWrapper, response);
    }

    private AccessLog logging(RequestWrapper request) throws IOException {
        return AccessLog.builder()
                .requestMethod(request.getMethod())
                .requestUrl(request.getRequestURI())
                .requestIp(RequestLogUtil.getIp(request))
                .requestBody(RequestLogUtil.getBody(request))
                .build();
    }
}
