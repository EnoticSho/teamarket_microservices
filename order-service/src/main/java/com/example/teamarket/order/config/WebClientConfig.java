package com.example.teamarket.order.config;

import com.example.teamarket.order.properties.CartServiceIntegrationProperties;
import com.example.teamarket.order.properties.UserServiceIntegrationProperties;
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
 * Configuration class for creating WebClient instances for communication with external services.
 */
@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties({CartServiceIntegrationProperties.class, UserServiceIntegrationProperties.class})
public class WebClientConfig {

    private final CartServiceIntegrationProperties cartProperties;
    private final UserServiceIntegrationProperties userProperties;

    /**
     * Creates a WebClient instance for communicating with the Cart Service.
     *
     * @return WebClient instance for the Cart Service.
     */
    @Bean
    public WebClient cartServiceWebClient() {
        return createWebClient(
                cartProperties.connectTimeout(),
                cartProperties.readTimeout(),
                cartProperties.writeTimeout(),
                cartProperties.url()
        );
    }

    /**
     * Creates a WebClient instance for communicating with the User Service.
     *
     * @return WebClient instance for the User Service.
     */
    @Bean
    public WebClient userServiceWebClient() {
        return createWebClient(
                userProperties.connectTimeout(),
                userProperties.readTimeout(),
                userProperties.writeTimeout(),
                userProperties.url()
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
