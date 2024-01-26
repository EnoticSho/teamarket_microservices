package com.example.teamarket.order.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "integrations.user-service")
public record UserServiceIntegrationProperties(
        String url,
        Integer connectTimeout,
        Integer readTimeout,
        Integer writeTimeout) {
}
