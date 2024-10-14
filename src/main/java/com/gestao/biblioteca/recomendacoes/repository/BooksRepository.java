package com.gestao.biblioteca.recomendacoes.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestao.biblioteca.recomendacoes.model.Books;

public interface BooksRepository extends JpaRepository<Books, UUID> {

	boolean existsByIsbn(String isbn);
	
	List<Books> findByCategory(String category);



}
