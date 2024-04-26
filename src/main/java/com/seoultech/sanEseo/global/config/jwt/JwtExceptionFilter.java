package com.seoultech.sanEseo.global.config.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seoultech.sanEseo.global.exception.BusinessException;
import com.seoultech.sanEseo.global.exception.ErrorType;
import com.seoultech.sanEseo.global.response.ApiResponse;
import com.seoultech.sanEseo.global.response.FailResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtExceptionFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (BusinessException e) {
            e.printStackTrace();
            setErrorResponse(e.getErrorType(), e.getMessage(), response);
        } catch (ConstraintViolationException e) {
            e.printStackTrace();
            setErrorResponse(ErrorType.INVALID_INPUT, "잘못된 값입니다.", response);
        } catch (Exception e) { // TODO : 예외가 위에서 안잡히고 여기서 잡힘
            e.printStackTrace();
            setErrorResponse(ErrorType.INTERNAL_ERROR, "서버 에러 " + e.getMessage(), response);
        }
    }

    public void setErrorResponse(ErrorType errorType, String message, HttpServletResponse response) throws IOException {
        response.setStatus(errorType.getStatusCode());
        response.setContentType("application/json; charset=UTF-8");

        FailResponse failResponse = new FailResponse(message, ErrorType.INVALID_JWT.getErrorCode());

        response.getWriter().write(objectMapper.writeValueAsString(failResponse));
    }
}
