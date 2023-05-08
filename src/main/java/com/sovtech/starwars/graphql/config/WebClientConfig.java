package com.sovtech.starwars.graphql.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author asopia
 */
@Configuration
public class WebClientConfig {

    private final Properties properties;

    public WebClientConfig(Properties properties) {
        this.properties = properties;
    }

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl(properties.getBaseUrl())
                .filter(new LoggingFilter())
                .build();
    }

}
