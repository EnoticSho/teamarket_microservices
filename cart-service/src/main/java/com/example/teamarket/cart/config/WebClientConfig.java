package com.example.teamarket.cart.config;

import com.example.teamarket.cart.properties.CoreServiceIntegrationProperties;
import com.example.teamarket.cart.properties.InventoryServiceIntegrationProperties;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

/**
 * Configuration class for creating and configuring WebClient instances to communicate with external services.
 */
@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties({CoreServiceIntegrationProperties.class, InventoryServiceIntegrationProperties.class})
public class WebClientConfig {

    private final CoreServiceIntegrationProperties coreServiceIntegrationProperties;
    private final InventoryServiceIntegrationProperties integrationProperties;


    @Bean
    public WebClient integrationServiceWebClient() {
        return createWebClient(
                integrationProperties.getConnectTimeout(),
                integrationProperties.getReadTimeout(),
                integrationProperties.getWriteTimeout(),
                integrationProperties.getUrl()
        );
    }

    /**
     * Configures and creates a WebClient for communicating with the product service.
     *
     * @return A WebClient instance for the product service with configured properties.
     */
    @Bean
    public WebClient productServiceWebClient() {
        return createWebClient(
                coreServiceIntegrationProperties.getConnectTimeout(),
                coreServiceIntegrationProperties.getReadTimeout(),
                coreServiceIntegrationProperties.getWriteTimeout(),
                coreServiceIntegrationProperties.getUrl()
        );
    }

    private WebClient createWebClient(Integer connectTimeout, Integer readTimeout, Integer writeTimeout, String url) {
        return WebClient
                .builder()
                .baseUrl(url)
                .clientConnector(new ReactorClientHttpConnector(HttpClient.create()
                        .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, connectTimeout)
                        .doOnConnected(connection -> {
                            connection.addHandlerLast(new ReadTimeoutHandler(readTimeout));
                            connection.addHandlerLast(new WriteTimeoutHandler(writeTimeout));
                        })))
                .build();
    }
}
