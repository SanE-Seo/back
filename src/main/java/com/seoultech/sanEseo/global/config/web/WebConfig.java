package com.seoultech.sanEseo.global.config.web;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

  private final LoginMemberArgumentResolver loginMemberArgumentResolver;

  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
    argumentResolvers.add(loginMemberArgumentResolver);
  }

  @Bean
  public FilterRegistrationBean requestLoggingFilter() {
    CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
    loggingFilter.setIncludeClientInfo(true);
    loggingFilter.setIncludeQueryString(true);
    loggingFilter.setIncludePayload(true);
    loggingFilter.setIncludeHeaders(false);
    loggingFilter.setMaxPayloadLength(1024 * 1024);
    FilterRegistrationBean bean = new FilterRegistrationBean(loggingFilter);
    return bean;
  }

  @Bean
  public FilterRegistrationBean commonLoggingFilter(){
    FilterRegistrationBean bean = new FilterRegistrationBean(new LoggingFilter());
    bean.setOrder(Integer.MIN_VALUE);
    return bean;
  }
}
