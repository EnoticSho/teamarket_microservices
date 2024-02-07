package com.example.teamarket.auth.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "integrations.cart-service")
public record CartServiceIntegrationProperties(
        String url,
        Integer connectTimeout,
        Integer readTimeout,
        Integer writeTimeout) {
}
