package com.example.teamarket.core.config;

import com.example.teamarket.core.properties.ReviewServiceIntegrationProperties;
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

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(ReviewServiceIntegrationProperties.class)
public class WebClientConfig {

    private final ReviewServiceIntegrationProperties properties;

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
