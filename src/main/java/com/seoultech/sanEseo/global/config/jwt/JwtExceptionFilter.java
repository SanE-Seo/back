package com.seoultech.sanEseo.global.config.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seoultech.sanEseo.global.common.ErrorLoggerHelper;
import com.seoultech.sanEseo.global.common.RequestWrapper;
import com.seoultech.sanEseo.global.exception.BusinessException;
import com.seoultech.sanEseo.global.exception.ErrorType;
import com.seoultech.sanEseo.global.response.ApiResponse;
import com.seoultech.sanEseo.global.response.FailResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtExceptionFilter extends OncePerRequestFilter {

    private final ErrorLoggerHelper errorLoggerHelper;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        RequestWrapper wrapper = new RequestWrapper(request);
        try {
            filterChain.doFilter(request, response);
        } catch (BusinessException e) {
            errorLoggerHelper.log(wrapper, e.getErrorType(), e.getMessage());
            setErrorResponse(e.getErrorType(), e.getMessage(), response);
        } catch (Exception e) {
            errorLoggerHelper.log(wrapper, ErrorType.INTERNAL_ERROR, e.getMessage());
            setErrorResponse(ErrorType.INTERNAL_ERROR, "서버 에러 ", response);
        }
    }

    public void setErrorResponse(ErrorType errorType, String message, HttpServletResponse response) throws IOException {
        response.setStatus(errorType.getStatusCode());
        response.setContentType("application/json; charset=UTF-8");

        FailResponse failResponse = new FailResponse(message, errorType.getErrorCode());

        response.getWriter().write(objectMapper.writeValueAsString(failResponse));
    }
}
