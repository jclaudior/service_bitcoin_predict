package br.com.jcr.hrbitcoinpredict.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebfluxConfig {
    @Bean
    public WebClient getWebClient()
    {
        return WebClient.builder()
                .baseUrl("https://brasilbitcoin.com.br/api")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
