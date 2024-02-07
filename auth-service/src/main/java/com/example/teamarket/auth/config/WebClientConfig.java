package com.example.teamarket.auth.config;

import com.example.teamarket.auth.properties.CartServiceIntegrationProperties;
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
@EnableConfigurationProperties({CartServiceIntegrationProperties.class})
public class WebClientConfig {

    private final CartServiceIntegrationProperties cartServiceIntegrationProperties;

    @Bean
    public WebClient cartServiceWebClient() {
        return createWebClient(
                cartServiceIntegrationProperties.connectTimeout(),
                cartServiceIntegrationProperties.readTimeout(),
                cartServiceIntegrationProperties.writeTimeout(),
                cartServiceIntegrationProperties.url()
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
