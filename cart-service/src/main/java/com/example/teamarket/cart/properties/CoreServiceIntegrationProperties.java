package com.example.teamarket.cart.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties for integrating with the CoreService.
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "integrations.core-service")
public class CoreServiceIntegrationProperties {
    private String url;            // The URL of the CoreService.
    private Integer connectTimeout; // Connection timeout duration in milliseconds.
    private Integer readTimeout;    // Read timeout duration in milliseconds.
    private Integer writeTimeout;   // Write timeout duration in milliseconds.
}
