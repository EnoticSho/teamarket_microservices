package com.example.teamarket.cart.config;

import com.example.teamarket.cart.properties.CoreServiceIntegrationProperties;
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
@EnableConfigurationProperties(CoreServiceIntegrationProperties.class)
public class WebClientConfig {

    private final CoreServiceIntegrationProperties properties;

    /**
     * Configures and creates a WebClient for communicating with the product service.
     *
     * @return A WebClient instance for the product service with configured properties.
     */
    @Bean
    public WebClient productServiceWebClient() {
        return WebClient
                .builder()
                .baseUrl(properties.getUrl())
                .clientConnector(new ReactorClientHttpConnector(HttpClient
                        .create()
                        .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, properties.getConnectTimeout())
                        .doOnConnected(connection -> {
                            connection.addHandlerLast(new ReadTimeoutHandler(properties.getReadTimeout()));
                            connection.addHandlerLast(new WriteTimeoutHandler(properties.getWriteTimeout()));
                        })))
                .build();
    }
}
