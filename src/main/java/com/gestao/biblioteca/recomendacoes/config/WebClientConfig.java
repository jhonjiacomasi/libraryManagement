package com.gestao.biblioteca.recomendacoes.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
	
	@Value("${google.books.api.url}")
	private String googleBooksApiUrl;
	
	@Bean
	public WebClient webClient(WebClient.Builder builder) {
		return builder.baseUrl(googleBooksApiUrl).build();
	}

}
