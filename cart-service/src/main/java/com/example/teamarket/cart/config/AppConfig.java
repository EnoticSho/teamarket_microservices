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
import reactor.netty.tcp.TcpClient;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(CoreServiceIntegrationProperties.class)
public class AppConfig {

    private final CoreServiceIntegrationProperties properties;

    @Bean
    public WebClient productServiceWebClient() {
        TcpClient tcpClient = TcpClient
                .create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, properties.getConnectTimeout())
                .doOnConnected(connection -> {
                    connection.addHandlerLast(new ReadTimeoutHandler(properties.getReadTimeout()));
                    connection.addHandlerLast(new WriteTimeoutHandler(properties.getWriteTimeout()));
                });
        return WebClient
                .builder()
                .baseUrl(properties.getUrl())
                .clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient)))
                .build();
    }
}
