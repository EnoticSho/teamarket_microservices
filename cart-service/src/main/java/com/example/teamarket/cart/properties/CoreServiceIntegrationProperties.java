package com.example.teamarket.cart.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties for integrating with the CoreService.
 */
@ConfigurationProperties(prefix = "integrations.core-service")
public record CoreServiceIntegrationProperties(String url,
                                               Integer connectTimeout,
                                               Integer readTimeout,
                                               Integer writeTimeout) {
}
