package com.example.teamarket.core.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties for integrating with the Review Service.
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "integrations.review-service")
public class ReviewServiceIntegrationProperties {
    private String url;
    private Integer connectTimeout;
    private Integer readTimeout;
    private Integer writeTimeout;
}
