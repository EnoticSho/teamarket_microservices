package com.example.teamarket.cart.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "integrations.core-service")
public class CoreServiceIntegrationProperties {
    private String url;
    private Integer connectTimeout;
    private Integer readTimeout;
    private Integer writeTimeout;
}
