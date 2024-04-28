package com.seoultech.sanEseo.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
class DataGoConfig {

    @Bean
    public DataGoAPI dataGoApi() {
        WebClient client = WebClient.create("https://apis.data.go.kr");

        return HttpServiceProxyFactory
                .builderFor(WebClientAdapter.create(client))
                .build()
                .createClient(DataGoAPI.class);
    }

}
