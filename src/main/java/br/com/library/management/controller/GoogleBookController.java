package br.com.library.management.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.library.management.model.GoogleBookRequest;
import br.com.library.management.service.GoogleBookService;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1")
public class GoogleBookController {

	private final GoogleBookService googleBookService;

	public GoogleBookController(GoogleBookService googleBookService) {
		super();
		this.googleBookService = googleBookService;
	}

	@PostMapping("/searchgoogleBooks")
	public Mono<String> searchBook(@RequestBody GoogleBookRequest bookRequest) {

		return googleBookService.searchBookByTitle(bookRequest.getTitle());
	}

}
