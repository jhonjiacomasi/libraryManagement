package com.gestao.biblioteca.recomendacoes.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.gestao.biblioteca.recomendacoes.dto.BooksDto;
import com.gestao.biblioteca.recomendacoes.model.Books;
import com.gestao.biblioteca.recomendacoes.repository.BooksRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class BookService {
	
private final BooksRepository booksRepository;

	
	public BookService(BooksRepository booksRepository) {
	super();
	this.booksRepository = booksRepository;
}

	@Transactional
	public Books saveBook(BooksDto booksDto){
		Books books = new Books(booksDto);
		return booksRepository.save(books);
	}

	public List<Books> getALLBooks() {
		return booksRepository.findAll();
	}
	
	public Optional<Books> getBooksById(UUID uuid) {
		return booksRepository.findById(uuid);
	}
	
	@Transactional
	public Books updateBook(UUID id, BooksDto booksDto) {
		return booksRepository.findById(id)
  			   .map(existingBooks-> updateExistingBook(existingBooks,booksDto))
 	          .orElseThrow(() -> new EntityNotFoundException("User not found with ID: "+ id));
	
	}
	
	private Books updateExistingBook(Books existingBooks,BooksDto booksDto) {
		existingBooks.setTitle(booksDto.getTitle());
		existingBooks.setAuthor(booksDto.getAuthor());
		existingBooks.setIsbn(booksDto.getIsbn());
		existingBooks.setPublishDate(booksDto.getPublishDate());
		existingBooks.setCategory(booksDto.getCategory());
		
		
		return booksRepository.save(existingBooks);
		
	}
	@Transactional
	public void deleteBookById(UUID uuid) {
		booksRepository.deleteById(uuid);
	}


}
