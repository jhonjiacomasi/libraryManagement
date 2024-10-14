package com.gestao.biblioteca.recomendacoes.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestao.biblioteca.recomendacoes.model.Books;
import com.gestao.biblioteca.recomendacoes.model.User;

import jakarta.validation.constraints.NotBlank;

public interface BooksRepository extends JpaRepository<Books, UUID> {

	Optional<Books> findById (Books books_id);

}
