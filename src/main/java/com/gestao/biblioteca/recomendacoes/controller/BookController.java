package com.gestao.biblioteca.recomendacoes.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestao.biblioteca.recomendacoes.dto.BooksDto;
import com.gestao.biblioteca.recomendacoes.model.Books;
import com.gestao.biblioteca.recomendacoes.service.BookService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class BookController {
	
	private final BookService bookService;
	
	@PostMapping("/savebook")
	public ResponseEntity<Books> saveBook(@RequestBody BooksDto booksDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(bookService.saveBook(booksDto));
	}

	@GetMapping("/allBooks")
	public ResponseEntity<List<Books>> getALLBooks() {

		return ResponseEntity.status(HttpStatus.CREATED).body(bookService.getALLBooks());
	}
	@GetMapping("/bookById/{id}")
	public ResponseEntity<Books> getBooksById(@PathVariable UUID id) {
		Optional<Books> book = bookService.getBooksById(id);
		return ResponseEntity.ok(book.get());
	}



	@PutMapping("/updateBook/{uuid}")
	public ResponseEntity<Books> updateUser(@PathVariable UUID uuid, @RequestBody @Valid BooksDto booksDto){
		try {
			Books updateBook = bookService.updateBook(uuid, booksDto);
			return ResponseEntity.ok(updateBook);
			
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
		
	}

	@DeleteMapping("/deletebook/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable UUID id) {
		bookService.deleteBookById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Book delete sucessfull");
	}

}
