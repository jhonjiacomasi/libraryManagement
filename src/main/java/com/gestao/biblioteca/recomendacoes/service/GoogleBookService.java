package com.gestao.biblioteca.recomendacoes.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	 private static final Logger logger = LoggerFactory.getLogger(GoogleBookService.class);

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

	        if (itemsNode.isArray() && itemsNode.size() > 0) {
	            JsonNode item = itemsNode.get(0);

	            String title = item.path("volumeInfo").path("title").asText(null);
	            String author = item.path("volumeInfo").path("authors").has(0) ? 
	                            item.path("volumeInfo").path("authors").get(0).asText(null) : "Unknown Author";
	            String isbn = item.path("volumeInfo").path("industryIdentifiers").has(0) ? 
	                          item.path("volumeInfo").path("industryIdentifiers").get(0).path("identifier").asText(null) : "Unknown ISBN";
	            Date publishDate = parsePublishDate(item.path("volumeInfo").path("publishedDate").asText(null));
	            String category = item.path("volumeInfo").path("categories").has(0) ? 
	                              item.path("volumeInfo").path("categories").get(0).asText(null) : "Unknown Category";
	            
	           
	            
	            if (!booksRepository.existsByIsbn(isbn)) {
	                Books book = new Books();
	                book.setTitle(title != null ? title : "Unknown Title");
	                book.setAuthor(author != null ? author : "Unknown Author");
	                book.setIsbn(isbn != null ? isbn : "Unknown ISBN");
	                book.setPublishDate(publishDate);
	                book.setCategory(category != null ? category : "Unknown Category");
	               
	                booksRepository.save(book);
	                logRecommendedBooks(recommendBooksByCategory(category));
	                return Mono.just(response);
	               
	                
	                
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


	private List<Books> recommendBooksByCategory(Object category) {
	    String categoryString = category.toString();
	    return booksRepository.findByCategory(categoryString);
	}

	private void logRecommendedBooks(List<Books> books) {
	    books.stream()
	        .limit(10)
	        .forEach(book -> logger.info("Recomendações: " + book.getTitle() + " por " + book.getAuthor()));
	}

	
	 private Date parsePublishDate(String publishDateStr) {
		    SimpleDateFormat fullFormat = new SimpleDateFormat("yyyy-MM-dd");
		    SimpleDateFormat yearOnlyFormat = new SimpleDateFormat("yyyy");
		    
		    try {
		        return fullFormat.parse(publishDateStr);
		    } catch (ParseException e) {
		        try {
		            return yearOnlyFormat.parse(publishDateStr);
		        } catch (ParseException ex) {
		        	logger.info("Error parsing date: " + publishDateStr);
		            return null; 
		        }
		    }
		}


	
   
	
}
