package com.example.teamarket.order.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties for integrating with the Cart service.
 */
@ConfigurationProperties(prefix = "integrations.cart-service")
public record CartServiceIntegrationProperties(
        String url,
        Integer connectTimeout,
        Integer readTimeout,
        Integer writeTimeout) {
}
