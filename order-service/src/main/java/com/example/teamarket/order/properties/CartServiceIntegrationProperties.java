package com.example.teamarket.order.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties for integrating with the Cart service.
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "integrations.cart-service")
public class CartServiceIntegrationProperties {
    private String url;
    private Integer connectTimeout;
    private Integer readTimeout;
    private Integer writeTimeout;
}
