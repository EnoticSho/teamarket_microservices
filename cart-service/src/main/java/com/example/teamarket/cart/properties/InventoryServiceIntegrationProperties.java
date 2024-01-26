package com.example.teamarket.cart.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties for integrating with the InventoryService.
 */
@ConfigurationProperties(prefix = "integrations.inventory-service")
public record InventoryServiceIntegrationProperties(String url,
                                                    Integer connectTimeout,
                                                    Integer readTimeout,
                                                    Integer writeTimeout) {
}
