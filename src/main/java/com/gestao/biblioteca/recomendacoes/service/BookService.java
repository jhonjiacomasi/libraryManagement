package com.gestao.biblioteca.recomendacoes.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.gestao.biblioteca.recomendacoes.dto.BooksDto;
import com.gestao.biblioteca.recomendacoes.exception.LibraryManagementException;
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
	public Books saveBook(BooksDto booksDto) throws EntityNotFoundException {
		try {
			Books books = new Books(booksDto);
			return booksRepository.save(books);
		} catch (EntityNotFoundException e) {
			throw new LibraryManagementException("Failed to save Book in Database: " + booksDto.title(), e);
		}

	}

	public List<Books> getALLBooks() {
		try {
			return booksRepository.findAll();
		
		}catch (Exception e) {
			throw new LibraryManagementException("Failed to Fecht books in Database : " + e);
		}
		
	}
	
	public Optional<Books> getBooksById(UUID uuid) {
		try {
			return booksRepository.findById(uuid);
		} catch (Exception e) {
			throw new LibraryManagementException("Failed to fecth Boos from the Database: " + uuid, e);
		}
		
	}
	
	@Transactional
	public Books updateBook(UUID id, BooksDto booksDto) {
		return booksRepository.findById(id).map(existingBooks -> updateExistingBook(existingBooks, booksDto))
				.orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + id));

	}

	private Books updateExistingBook(Books existingBooks, BooksDto booksDto) {
		existingBooks.setTitle(booksDto.title());
		existingBooks.setAuthor(booksDto.author());
		existingBooks.setIsbn(booksDto.isbn());
		existingBooks.setPublishDate(booksDto.publishDate());
		existingBooks.setCategory(booksDto.category());
		return booksRepository.save(existingBooks);
	}
	
	@Transactional
	public void deleteBookById(UUID uuid) {
		try {
			booksRepository.deleteById(uuid);
		} catch (Exception e) {
			throw new LibraryManagementException("Failed to Delete Boos from the Database: " + uuid, e);
		}

	}

}
