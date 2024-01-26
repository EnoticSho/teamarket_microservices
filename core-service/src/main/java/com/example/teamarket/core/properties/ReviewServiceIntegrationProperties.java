package com.example.teamarket.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties for integrating with the Review Service.
 */
@ConfigurationProperties(prefix = "integrations.review-service")
public record ReviewServiceIntegrationProperties(
        String url,
        Integer connectTimeout,
        Integer readTimeout,
        Integer writeTimeout) {
}
