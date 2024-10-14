package com.gestao.biblioteca.recomendacoes.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gestao.biblioteca.recomendacoes.model.Books;
import com.gestao.biblioteca.recomendacoes.repository.BooksRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class GoogleBookService {
	
	private final BooksRepository booksRepository;
	
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
			   .flatMap(this::processAndSaveBooks)
			   .onErrorResume(WebClientResponseException.class, ex ->{
				   return Mono.error(new RuntimeException("Error Runtime: " + ex.getResponseBodyAsString()));
			   });
	}
	
	 private Mono<String> processAndSaveBooks(String response) {
	        ObjectMapper objectMapper = new ObjectMapper();
	        try {
	            JsonNode itemsNode = objectMapper.readTree(response).path("items");

	            // Verifica se existe ao menos um item
	            if (itemsNode.isArray() && itemsNode.size() > 0) {
	                // Usa o primeiro item encontrado
	                JsonNode item = itemsNode.get(0);
	                
	                String title = item.path("volumeInfo").path("title").asText();
	                String author = item.path("volumeInfo").path("authors").get(0).asText();
	                String isbn = item.path("volumeInfo").path("industryIdentifiers").get(0).path("identifier").asText();
	                Date publishDate = parsePublishDate(item.path("volumeInfo").path("publishedDate").asText());
	                String category = item.path("volumeInfo").path("categories").get(0).asText();

	                if (!booksRepository.existsByIsbn(isbn)) {
	                    Books book = new Books();
	                    book.setTitle(title);
	                    book.setAuthor(author);
	                    book.setIsbn(isbn);
	                    book.setPublishDate(publishDate);
	                    book.setCategory(category);
	                    booksRepository.save(book);
	                    return Mono.just("Book saved successfully");
	                } else {
	                    return Mono.just("Book with ISBN " + isbn + " already exists.");
	                }
	            } else {
	                return Mono.error(new RuntimeException("No book items found in the response"));
	            }
	        } catch (Exception e) {
	            return Mono.error(new RuntimeException("Error processing the book data: " + e.getMessage()));
	        }
	    }

	 private Date parsePublishDate(String publishDateStr) {
	        try {
	            return new SimpleDateFormat("yyyy-MM-dd").parse(publishDateStr);
	        } catch (ParseException e) {
	            System.err.println("Error parsing date: " + e.getMessage());
	            return null; // Ou retornar uma data padrão, se necessário
	        }
	    }

	
   
	
}
