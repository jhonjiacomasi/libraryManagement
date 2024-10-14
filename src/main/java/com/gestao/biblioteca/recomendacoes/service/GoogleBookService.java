package com.gestao.biblioteca.recomendacoes.service;

import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class GoogleBookService {
	
	private final WebClient webClient;

	@Value("${google.books.api.key}")
	private String apikey;
	
	public Mono<String> searchBookByTitle(String title){
		return webClient.get()
			     .uri(uriBuilder -> uriBuilder
			     .path("")
			     .queryParam("q",title)
			     .queryParam("key",apikey)
			     .build())
			   .retrieve()
			   .onStatus(
			    	status-> status.is4xxClientError()|| status.is5xxServerError(),
			    	clienteResponse->Mono.error(new RuntimeException("Eror Response:"+ clienteResponse.statusCode()))
			   )
			   .bodyToMono(String.class)
			   .onErrorResume(WebClientResponseException.class, ex ->{
				   return Mono.error(new RuntimeException("Error Runtime: " + ex.getResponseBodyAsString()));
			   });
	}
	
	
   
	
}
