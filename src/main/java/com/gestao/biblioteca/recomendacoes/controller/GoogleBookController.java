package com.gestao.biblioteca.recomendacoes.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gestao.biblioteca.recomendacoes.model.GoogleBookRequest;
import com.gestao.biblioteca.recomendacoes.service.GoogleBookService;

import reactor.core.publisher.Mono;

@RestController
public class GoogleBookController {
	
	private final GoogleBookService googleBookService;

	public GoogleBookController(GoogleBookService googleBookService) {
		super();
		this.googleBookService = googleBookService;
	}

	@PostMapping("/searchBook")
	public Mono<String> searchBook(@RequestBody GoogleBookRequest bookRequest) {

		return googleBookService.searchBookByTitle(bookRequest.getTitle());
	}
	
}
