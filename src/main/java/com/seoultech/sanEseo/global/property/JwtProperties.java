package com.seoultech.sanEseo.global.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Setter
@Getter
@Component
@ConfigurationProperties("jwt")
public class JwtProperties {
    private String secretKey;
    private int accessExpiredMin;
    private int refreshExpiredDay;
}
