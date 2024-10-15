package br.com.library.management.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.library.management.model.Books;

public interface BooksRepository extends JpaRepository<Books, UUID> {

	boolean existsByIsbn(String isbn);
	
	List<Books> findByCategory(String category);
	



}
