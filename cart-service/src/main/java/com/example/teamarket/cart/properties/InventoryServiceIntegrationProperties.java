package com.example.teamarket.cart.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties for integrating with the InventoryService.
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "integrations.inventory-service")
public class InventoryServiceIntegrationProperties {
    private String url;
    private Integer connectTimeout;
    private Integer readTimeout;
    private Integer writeTimeout;
}
