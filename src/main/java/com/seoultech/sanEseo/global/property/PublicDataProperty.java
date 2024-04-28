package com.seoultech.sanEseo.global.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ConfigurationProperties("public-data")
public class PublicDataProperty {
    private String accessKey;
    private String seoulAccessKey;
}
